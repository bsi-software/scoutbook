/**
 * 
 */
package org.eclipsescout.mqttclient.shared.services;

import java.security.BasicPermission;

/**
 * @author mzi
 */
public class UpdateMqttPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public UpdateMqttPermission() {
    super("UpdateMqtt");
  }
}
