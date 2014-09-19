/**
 *
 */
package org.eclipsescout.mqttclient.client.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

/**
 * @author mzi
 */
public interface IUserPreferencesService extends IService {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void load() throws ProcessingException;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void store() throws ProcessingException;

  /**
   * @param url
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void setBrokerUrl(String url) throws ProcessingException;

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  String getBrokerUrl() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getClientId() throws ProcessingException;

  /**
   * @param id
   * @throws ProcessingException
   */
  void setClientId(String id) throws ProcessingException;

  /**
   * @param userName
   * @throws ProcessingException
   */
  void setUserName(String userName) throws ProcessingException;

  /**
   * @param password
   * @throws ProcessingException
   */
  void setPassword(String password) throws ProcessingException;

  /**
   * @param connectionTimeout
   * @throws ProcessingException
   */
  void setConnectionTimeout(String connectionTimeout) throws ProcessingException;

  /**
   * @param cleanSession
   * @throws ProcessingException
   */
  void setCleanSession(String cleanSession) throws ProcessingException;

  /**
   * @param topic
   * @throws ProcessingException
   */
  void setWillTopic(String topic) throws ProcessingException;

  /**
   * @param message
   * @throws ProcessingException
   */
  void setWillMessage(String message) throws ProcessingException;

  /**
   * @param qos
   * @throws ProcessingException
   */
  void setWillQos(String qos) throws ProcessingException;

  /**
   * @param retained
   * @throws ProcessingException
   */
  void setWillRetained(String retained) throws ProcessingException;

  /**
   * @param topic
   * @throws ProcessingException
   */
  void setDefaultTopic(String topic) throws ProcessingException;

  /**
   * @param qos
   * @throws ProcessingException
   */
  void setDefaultQos(String qos) throws ProcessingException;

  /**
   * @param retained
   * @throws ProcessingException
   */
  void setDefaultRetained(String retained) throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getUserName() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getPassword() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getConnectionTimeout() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getCleanSession() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getWillTopic() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getWillMessage() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getWillQos() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getWillRetained() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getDefaultTopic() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getDefaultQos() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  String getDefaultRetained() throws ProcessingException;
}
