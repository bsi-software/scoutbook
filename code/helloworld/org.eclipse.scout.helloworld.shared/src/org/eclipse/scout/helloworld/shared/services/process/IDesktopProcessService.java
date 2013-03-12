package org.eclipse.scout.helloworld.shared.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

public interface IDesktopProcessService extends IService {

  public DesktopFormData load(DesktopFormData formData) throws ProcessingException;
}
