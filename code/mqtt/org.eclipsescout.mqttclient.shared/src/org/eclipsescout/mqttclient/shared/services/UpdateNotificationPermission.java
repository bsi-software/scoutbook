/**
 * 
 */
package org.eclipsescout.mqttclient.shared.services;

import java.security.BasicPermission;

/**
 * @author mzi
 */
public class UpdateNotificationPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public UpdateNotificationPermission() {
    super("UpdateNotification");
  }
}
