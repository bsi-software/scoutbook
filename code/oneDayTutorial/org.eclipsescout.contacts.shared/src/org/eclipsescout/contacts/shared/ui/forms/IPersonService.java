package org.eclipsescout.contacts.shared.ui.forms;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IPersonService extends IService2 {

  PersonFormData prepareCreate(PersonFormData formData) throws ProcessingException;

  PersonFormData create(PersonFormData formData) throws ProcessingException;

  PersonFormData load(PersonFormData formData) throws ProcessingException;

  PersonFormData store(PersonFormData formData) throws ProcessingException;
}
