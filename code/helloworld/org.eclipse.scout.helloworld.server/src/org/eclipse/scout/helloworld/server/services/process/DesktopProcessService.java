package org.eclipse.scout.helloworld.server.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.helloworld.shared.services.process.DesktopFormData;
import org.eclipse.scout.helloworld.shared.services.process.IDesktopProcessService;
import org.eclipse.scout.service.AbstractService;

public class DesktopProcessService extends AbstractService implements IDesktopProcessService {

  @Override
  public DesktopFormData load(DesktopFormData formData) throws ProcessingException {
    formData.getMessage().setValue("hello world");
    return formData;
  }
}
