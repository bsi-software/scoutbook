package org.eclipse.scout.helloworld.shared.services.process;

import org.eclipse.scout.service.IService;
import org.eclipse.scout.helloworld.shared.services.process.DesktopFormData;
import org.eclipse.scout.commons.exception.ProcessingException;

public interface IDesktopProcessService extends IService{

  public DesktopFormData load(DesktopFormData formData) throws ProcessingException;
}
