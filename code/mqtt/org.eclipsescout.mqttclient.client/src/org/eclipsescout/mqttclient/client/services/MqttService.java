/**
 *
 */
package org.eclipsescout.mqttclient.client.services;

import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.scout.commons.BooleanUtility;
import org.eclipse.scout.commons.NumberUtility;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.mqttclient.client.ClientSession;

/**
 * @author mzi
 */
public class MqttService extends AbstractService implements IMqttService, MqttCallback {
  private static IScoutLogger s_logger = ScoutLogManager.getLogger(MqttService.class);

  private IClientSession m_session;
  private MqttClient m_mqttClient = null;
  private boolean m_isConnected = false;

  @Override
  public void setup(String broker, String clientId) throws ProcessingException {
    try {
      MemoryPersistence persistence = new MemoryPersistence();

      m_session = ClientSession.get();
      m_mqttClient = new MqttClient(broker, clientId, persistence);
      m_mqttClient.setCallback(this);
    }
    catch (Exception e) {
      throw new ProcessingException(TEXTS.get("MqttClientCreateException"), e);
    }
  }

  @Override
  public void connect(String userName, String password, Boolean clearSession, Integer connectionTimeout, String lwtTopic, String lwtMessage, Integer qos, Boolean lwtRetained) throws ProcessingException {
    if (m_mqttClient == null) {
      throw new ProcessingException("no mqtt client instance available, call method 'setup' first");
    }

    try {
      MqttConnectOptions options = getConnectOptions(userName, password, clearSession, connectionTimeout, lwtTopic, lwtMessage, qos, lwtRetained);
      m_mqttClient.connect(options);
      m_isConnected = true;
    }
    catch (Exception e) {
      throw new ProcessingException("an exception ocurred while connecting to the mqtt broker", e);
    }
  }

  private MqttConnectOptions getConnectOptions(String userName, String password, Boolean clearSession, Integer connectionTimeout, String lwtTopic, String lwtMessage, Integer lwtQos, Boolean lwtRetained) {
    MqttConnectOptions connectOpts = new MqttConnectOptions();

    if (!StringUtility.isNullOrEmpty(userName)) {
      connectOpts.setUserName(userName);

      if (!StringUtility.isNullOrEmpty(password)) {
        connectOpts.setPassword(password.toCharArray());
      }
    }

    if (clearSession != null) {
      connectOpts.setCleanSession(clearSession);
    }

    if (connectionTimeout != null) {
      connectOpts.setConnectionTimeout(connectionTimeout);
    }

    if (!StringUtility.isNullOrEmpty(lwtTopic) && !StringUtility.isNullOrEmpty(lwtMessage)) {
      connectOpts.setWill(
          lwtTopic,
          lwtMessage.getBytes(),
          NumberUtility.nvl(lwtQos, 1),
          BooleanUtility.nvl(lwtRetained, false));
    }

    return connectOpts;
  }

  @Override
  public void connectionLost(Throwable t) {
    s_logger.error("connection to mqtt broker lost. reason: " + t);

    m_isConnected = false;

    if (m_session != null) {
      new ClientJob("mqtt connection lost", m_session, true) {

        @Override
        protected void runVoid(IProgressMonitor monitor) throws Exception {
          MessageHandlingService service = SERVICES.getService(MessageHandlingService.class);
          service.handleDisconnect();
        }
      }.schedule();
    }
    else {
      s_logger.error("client session is null");
    }
  }

  @Override
  public void deliveryComplete(IMqttDeliveryToken t) {
    s_logger.info("delivery of mqtt message completed. deliveryToken=" + t);
  }

  @Override
  public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
    s_logger.info("mqtt message arrived. message " + topic + ":'" + new String(mqttMessage.getPayload()) + "' qos=" + mqttMessage.getQos() + " retained=" + mqttMessage.isRetained());

    final String message = new String(mqttMessage.getPayload());
    final int qos = mqttMessage.getQos();
    final boolean retained = mqttMessage.isRetained();
    final Date received = new Date();

    if (m_session != null) {
      new ClientJob("mqtt message arrived", m_session, true) {

        @Override
        protected void runVoid(IProgressMonitor monitor) throws Exception {
          MessageHandlingService service = SERVICES.getService(MessageHandlingService.class);
          service.handleMessage(topic, message, qos, retained, received);
        }
      }.schedule();
    }
    else {
      s_logger.error("client session is null");
    }
  }

  @Override
  public void disconnect() throws ProcessingException {
    checkConnection();

    try {
      m_mqttClient.disconnect();
      m_isConnected = false;
    }
    catch (MqttException e) {
      throw new ProcessingException(TEXTS.get("mqttException"), e);
    }
  }

  @Override
  public void publish(String topic, String content, Integer qos, Boolean retained) throws ProcessingException {
    checkConnection();

    try {
      MqttMessage message = new MqttMessage(content.getBytes());

      if (qos != null) {
        message.setQos(qos);
      }

      if (retained != null) {
        message.setRetained(retained);
      }

      m_mqttClient.publish(topic, message);
    }
    catch (Exception e) {
      throw new ProcessingException(TEXTS.get("publishError"), e);
    }

    s_logger.info("message " + topic + ":'" + content + "' successfully published");
  }

  @Override
  public void subscribe(String topicFilter, Integer qos) throws ProcessingException {
    checkConnection();

    try {
      if (qos == null) {
        m_mqttClient.subscribe(topicFilter);
      }
      else {
        m_mqttClient.subscribe(topicFilter, qos);
      }
    }
    catch (Exception e) {
      throw new ProcessingException(TEXTS.get("subscribeError"), e);
    }

    s_logger.info("topic " + topicFilter + "' successfully subscribed");
  }

  @Override
  public void unsubscribe(String topicFilter) throws ProcessingException {
    checkConnection();

    try {
      m_mqttClient.unsubscribe(topicFilter);
    }
    catch (Exception e) {
      throw new ProcessingException(TEXTS.get("unsubscribeError"), e);
    }
  }

  private void checkConnection() throws ProcessingException {
    if (m_mqttClient == null) {
      throw new ProcessingException(TEXTS.get("noClient"));
    }

    if (!m_mqttClient.isConnected()) {
      throw new ProcessingException(TEXTS.get("noConnection"));
    }
  }

  @Override
  public boolean isConnected() throws ProcessingException {
    return m_isConnected;
  }
}
