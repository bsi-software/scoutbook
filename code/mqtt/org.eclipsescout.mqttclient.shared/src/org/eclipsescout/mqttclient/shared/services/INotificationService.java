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
public interface INotificationService extends IService {

  /**
   * @param topic
   * @param message
   * @param retained
   * @param qos
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void sendMessage(String topic, String message, int qos, boolean retained) throws ProcessingException;
}
