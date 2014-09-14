/**
 *
 */
package org.eclipsescout.mqttclient.shared.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author mzi
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IMqttService extends IService {

  /**
   * @param userName
   * @param password
   * @param clearSession
   * @param connectionTimeout
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void connect(String userName, String password, boolean clearSession, int connectionTimeout) throws ProcessingException;

  /**
   * @param broker
   * @param clientId
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void setup(String broker, String clientId) throws ProcessingException;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void disconnect() throws ProcessingException;

  /**
   * @param topic
   * @param content
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void publish(String topic, String content) throws ProcessingException;

  /**
   * @param topic
   * @param content
   * @param qos
   * @param retained
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void publish(String topic, String content, Integer qos, Boolean retained) throws ProcessingException;

  /**
   * @param topicFilter
   * @throws ProcessingException
   */
  void subscribe(String topicFilter) throws ProcessingException;

  /**
   * @param topicFilter
   * @param qos
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void subscribe(String topicFilter, Integer qos) throws ProcessingException;

  /**
   * @param topicFilter
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void unsubscribe(String topicFilter) throws ProcessingException;

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  boolean isConnected() throws ProcessingException;

}
