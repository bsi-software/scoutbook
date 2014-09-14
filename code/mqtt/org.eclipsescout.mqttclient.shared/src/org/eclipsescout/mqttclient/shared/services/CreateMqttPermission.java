/**
 * 
 */
package org.eclipsescout.mqttclient.shared.services;

import java.security.BasicPermission;

/**
 * @author mzi
 */
public class CreateMqttPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public CreateMqttPermission() {
    super("CreateMqtt");
  }
}
