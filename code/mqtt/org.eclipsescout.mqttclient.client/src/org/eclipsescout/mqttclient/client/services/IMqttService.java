/**
 *
 */
package org.eclipsescout.mqttclient.client.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

/**
 * @author mzi
 */
public interface IMqttService extends IService {

  /**
   * @param broker
   * @param clientId
   * @throws ProcessingException
   */
  void setup(String broker, String clientId) throws ProcessingException;

  /**
   * @param userName
   * @param password
   * @param clearSession
   * @param connectionTimeout
   * @param lwtTopic
   * @param lwtMessage
   * @param qos
   * @param lwtRetained
   * @throws ProcessingException
   */
  void connect(String userName, String password, Boolean clearSession, Integer connectionTimeout, String lwtTopic, String lwtMessage, Integer qos, Boolean lwtRetained) throws ProcessingException;

  /**
   * @throws ProcessingException
   */
  void disconnect() throws ProcessingException;

  /**
   * @param topic
   * @param content
   * @param qos
   * @param retained
   * @throws ProcessingException
   */
  void publish(String topic, String content, Integer qos, Boolean retained) throws ProcessingException;

  /**
   * @param topicFilter
   * @param qos
   * @throws ProcessingException
   */
  void subscribe(String topicFilter, Integer qos) throws ProcessingException;

  /**
   * @param topicFilter
   * @throws ProcessingException
   */
  void unsubscribe(String topicFilter) throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  boolean isConnected() throws ProcessingException;

}
