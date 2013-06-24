package org.eclipsescout.contacts.shared.ui.forms;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface ICompanyService extends IService2 {

  CompanyFormData prepareCreate(CompanyFormData formData) throws ProcessingException;

  CompanyFormData create(CompanyFormData formData) throws ProcessingException;

  CompanyFormData load(CompanyFormData formData) throws ProcessingException;

  CompanyFormData store(CompanyFormData formData) throws ProcessingException;
}
