/**
 *
 */
package org.eclipsescout.mqttclient.server.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.AbstractService;
import org.eclipsescout.mqttclient.shared.services.DesktopFormData;
import org.eclipsescout.mqttclient.shared.services.IDesktopService;

/**
 * @author mzi
 */
public class DesktopService extends AbstractService implements IDesktopService {

  @Override
  public DesktopFormData load(DesktopFormData formData) throws ProcessingException {
    return formData;
  }
}
