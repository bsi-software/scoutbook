/**
 *
 */
package org.eclipsescout.mqttclient.server.services;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.ServerJob;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.mqttclient.server.ServerSession;
import org.eclipsescout.mqttclient.shared.services.IMqttService;
import org.eclipsescout.mqttclient.shared.services.INotificationService;

/**
 * @author mzi
 */
public class MqttService extends AbstractService implements IMqttService, MqttCallback {
  private static IScoutLogger s_logger = ScoutLogManager.getLogger(MqttService.class);

  private MqttClient m_mqttClient = null;
  private boolean m_isConnected = false;

  private ServerSession m_session;

  @Override
  public void setup(String broker, String clientId) throws ProcessingException {
    if (m_mqttClient == null) {
      m_mqttClient = createMqttClient(broker, clientId);
    }
    else {
      s_logger.warn(TEXTS.get("MqttClientExistsWarning") + ": " + m_mqttClient);
    }
  }

  private MqttClient createMqttClient(String broker, String clientId) throws ProcessingException {
    try {
      MemoryPersistence persistence = new MemoryPersistence();
      MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
      mqttClient.setCallback(this);
      return mqttClient;
    }
    catch (Exception e) {
      throw new ProcessingException(TEXTS.get("MqttClientCreateException"), e);
    }
  }

  private void closeMqttClient(MqttClient mqttClient) throws ProcessingException {
    try {
      if (mqttClient != null && mqttClient.isConnected()) {
        mqttClient.close();
      }
    }
    catch (Exception e) {
      throw new ProcessingException(TEXTS.get("MqttClientCreateException"), e);
    }
  }

  @Override
  public void connect(String userName, String password, boolean clearSession, int connectionTimeout) throws ProcessingException {
    if (m_mqttClient == null) {
      throw new ProcessingException("no mqtt client instance available, call method 'setup' first");
    }

    try {
      MqttConnectOptions options = getConnectOptions(userName, password, clearSession, connectionTimeout);
      m_mqttClient.connect(options);
      m_session = ServerSession.get();
      m_isConnected = true;
    }
    catch (Exception e) {
      throw new ProcessingException("an exception ocurred while connecting to the mqtt broker", e);
    }
  }

  private MqttConnectOptions getConnectOptions(String userName, String password, boolean clearSession, int connectionTimeout) {
    MqttConnectOptions connectOpts = new MqttConnectOptions();

    if (!StringUtility.isNullOrEmpty(userName)) {
      connectOpts.setUserName(userName);
      connectOpts.setPassword(password.toCharArray());
    }

    connectOpts.setCleanSession(clearSession);
    connectOpts.setConnectionTimeout(connectionTimeout);

    return connectOpts;
  }

  @Override
  public void connectionLost(Throwable t) {
    s_logger.error("connection to mqtt broker lost. reason: " + t);
    m_isConnected = false;
  }

  @Override
  public void deliveryComplete(IMqttDeliveryToken t) {
    s_logger.info("delivery of mqtt message completed. deliveryToken=" + t);
  }

  @Override
  public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
    s_logger.info("mqtt message arrived. message " + topic + ":'" + new String(mqttMessage.getPayload()) + "'");
    final String message = new String(mqttMessage.getPayload());
    final int qos = mqttMessage.getQos();
    final boolean retained = mqttMessage.isRetained();

    new ServerJob("messageArrived_MQTT", m_session) {

      @Override
      protected IStatus runTransaction(IProgressMonitor monitor) throws Exception {
        INotificationService service = SERVICES.getService(INotificationService.class);
        service.sendMessage(topic, message, qos, retained);
        return Status.OK_STATUS;
      }
    }.schedule();
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
  public void publish(String topic, String content) throws ProcessingException {
    publish(topic, content, null, null);
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
  public void subscribe(String topicFilter) throws ProcessingException {
    subscribe(topicFilter, null);
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
