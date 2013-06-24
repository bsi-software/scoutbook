package org.eclipsescout.contacts.shared.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface ILinkedInService extends IService {

  public String[] getAuthUrl() throws ProcessingException;

  public void refreshToken(String token, String secret, String securityCode) throws ProcessingException;

  public void updateContacts() throws ProcessingException;
}
