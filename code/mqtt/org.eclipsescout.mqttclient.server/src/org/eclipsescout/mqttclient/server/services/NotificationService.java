/**
 *
 */
package org.eclipsescout.mqttclient.server.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.clientnotification.IClientNotificationService;
import org.eclipse.scout.rt.server.services.common.clientnotification.SingleUserFilter;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.mqttclient.server.ServerSession;
import org.eclipsescout.mqttclient.shared.notification.MessageNotification;
import org.eclipsescout.mqttclient.shared.services.INotificationService;

/**
 * @author mzi
 */
public class NotificationService extends AbstractService implements INotificationService {
  private final static long TIMEOUT = 1000 * 60 * 10; // 10 min

  private static IScoutLogger s_logger = ScoutLogManager.getLogger(NotificationService.class);

  @Override
  public void sendMessage(String topic, String message, int qos, boolean retained) throws ProcessingException {
    IClientNotificationService service = SERVICES.getService(IClientNotificationService.class);
    service.putNotification(new MessageNotification(topic, message, qos, retained), new SingleUserFilter(ServerSession.get().getUserId(), TIMEOUT));
  }
}
