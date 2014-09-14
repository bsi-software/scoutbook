/**
 *
 */
package org.eclipsescout.mqttclient.client.services;

import java.util.Date;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

/**
 * @author mzi
 */
public interface IMessageHandlingService extends IService {

  /**
   * @param topic
   * @param message
   * @param qos
   * @param retained
   * @param received
   * @throws ProcessingException
   */
  void handleMessage(String topic, String message, int qos, boolean retained, Date received) throws ProcessingException;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void handleDisconnect() throws ProcessingException;
}
