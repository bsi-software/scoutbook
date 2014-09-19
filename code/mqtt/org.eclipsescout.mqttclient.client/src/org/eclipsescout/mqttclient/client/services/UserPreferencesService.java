/**
 *
 */
package org.eclipsescout.mqttclient.client.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.prefs.UserPreferences;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.AbstractService;
import org.osgi.service.prefs.BackingStoreException;

/**
 * Handles reading and saving of user preferences at startup and shutdown of application.
 * On windows, the preferences are written into "C:\\Documents and Settings\\<USER>\\user\\.settings"
 *
 * @author mzi
 */
public class UserPreferencesService extends AbstractService implements IUserPreferencesService {
  private static final String PREF_DOMAIN = "ScoutMqttClient";

  private static final String PREF_BROKER_URL = "PREF_BROKER_URL";
  private static final String PREF_CLIENT_ID = "PREF_CLIENT_ID";
  private static final String PREF_USER_NAME = "PREF_USER_NAME";
  private static final String PREF_PASSWORD = "PREF_PASSWORD";
  private static final String PREF_CONNECTION_TIMEOUT = "PREF_CONNECTION_TIMEOUT";
  private static final String PREF_CLEAN_SESSION = "PREF_CLEAN_SESSION";
  private static final String PREF_WILL_TOPIC = "PREF_WILL_TOPIC";
  private static final String PREF_WILL_MESSAGE = "PREF_WILL_MESSAGE";
  private static final String PREF_WILL_QOS = "PREF_WILL_QOS";
  private static final String PREF_WILL_RETAINED = "PREF_WILL_RETAINED";
  private static final String PREF_DEFAULT_TOPIC = "PREF_DEFAULT_TOPIC";
  private static final String PREF_DEFAULT_QOS = "PREF_DEFAULT_QOS";
  private static final String PREF_DEFAULT_RETAINED = "PREF_DEFAULT_RETAINED";

  private static final String BROKER_URL = "tcp://localhost:1883";
  private static final String CLIENT_ID = "ScoutMqttClient";
  private static final String USER_NAME = "user1";
  private static final String PASSWORD = "pw1";
  private static final String CONNECTION_TIMEOUT = "30";
  private static final String CLEAN_SESSION = "true";
  private static final String WILL_TOPIC = "ScoutMqttClient";
  private static final String WILL_MESSAGE = "Disconnected";
  private static final String WILL_QOS = "1";
  private static final String WILL_RETAINED = "true";
  private static final String DEFAULT_TOPIC = "ScoutMqttClient";
  private static final String DEFAULT_QOS = "1";
  private static final String DEFAULT_RETAINED = "false";

  private UserPreferences m_pref = null;

  public UserPreferencesService() {
    super();
    m_pref = new UserPreferences(PREF_DOMAIN);
  }

  @Override
  public void load() throws ProcessingException {
    m_pref.create();
  }

  @SuppressWarnings("restriction")
  @Override
  public void store() throws ProcessingException {
    try {
      m_pref.flush();
    }
    catch (BackingStoreException e) {
      throw new ProcessingException(TEXTS.get("PrefFlushFailed"), e);
    }
  }

  @SuppressWarnings("restriction")
  @Override
  public void setBrokerUrl(String url) throws ProcessingException {
    m_pref.put(PREF_BROKER_URL, url);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getBrokerUrl() throws ProcessingException {
    return m_pref.get(PREF_BROKER_URL, BROKER_URL);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setClientId(String id) throws ProcessingException {
    m_pref.put(PREF_CLIENT_ID, id);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getClientId() throws ProcessingException {
    return m_pref.get(PREF_CLIENT_ID, null);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setUserName(String userName) throws ProcessingException {
    m_pref.put(PREF_USER_NAME, userName);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setPassword(String password) throws ProcessingException {
    m_pref.put(PREF_PASSWORD, password);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setConnectionTimeout(String connectionTimeout) throws ProcessingException {
    m_pref.put(PREF_CONNECTION_TIMEOUT, connectionTimeout);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setCleanSession(String cleanSession) throws ProcessingException {
    m_pref.put(PREF_CLEAN_SESSION, cleanSession);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setWillTopic(String topic) throws ProcessingException {
    m_pref.put(PREF_WILL_TOPIC, topic);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setWillMessage(String message) throws ProcessingException {
    m_pref.put(PREF_WILL_MESSAGE, message);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setWillQos(String qos) throws ProcessingException {
    m_pref.put(PREF_WILL_QOS, qos);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setWillRetained(String retained) throws ProcessingException {
    m_pref.put(PREF_WILL_RETAINED, retained);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setDefaultTopic(String topic) throws ProcessingException {
    m_pref.put(PREF_DEFAULT_TOPIC, topic);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setDefaultQos(String qos) throws ProcessingException {
    m_pref.put(PREF_DEFAULT_QOS, qos);
  }

  @SuppressWarnings("restriction")
  @Override
  public void setDefaultRetained(String retained) throws ProcessingException {
    m_pref.put(PREF_DEFAULT_RETAINED, retained);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getUserName() throws ProcessingException {
    return m_pref.get(PREF_USER_NAME, USER_NAME);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getPassword() throws ProcessingException {
    return m_pref.get(PREF_PASSWORD, PASSWORD);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getConnectionTimeout() throws ProcessingException {
    return m_pref.get(PREF_CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getCleanSession() throws ProcessingException {
    return m_pref.get(PREF_CLEAN_SESSION, CLEAN_SESSION);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getWillTopic() throws ProcessingException {
    return m_pref.get(PREF_WILL_TOPIC, WILL_TOPIC);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getWillMessage() throws ProcessingException {
    return m_pref.get(PREF_WILL_MESSAGE, WILL_MESSAGE);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getWillQos() throws ProcessingException {
    return m_pref.get(PREF_WILL_QOS, WILL_QOS);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getWillRetained() throws ProcessingException {
    return m_pref.get(PREF_WILL_RETAINED, WILL_RETAINED);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getDefaultTopic() throws ProcessingException {
    return m_pref.get(PREF_DEFAULT_TOPIC, DEFAULT_TOPIC);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getDefaultQos() throws ProcessingException {
    return m_pref.get(PREF_DEFAULT_QOS, DEFAULT_QOS);
  }

  @SuppressWarnings("restriction")
  @Override
  public String getDefaultRetained() throws ProcessingException {
    return m_pref.get(PREF_DEFAULT_RETAINED, DEFAULT_RETAINED);
  }
}
