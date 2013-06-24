package org.eclipsescout.contacts.shared.services;

import org.eclipse.scout.service.IService2;
import org.eclipse.scout.commons.exception.ProcessingException;

public interface IStandardOutlineService extends IService2 {

  public Object[][] getPersonTableData(String companyId) throws ProcessingException;

  public Object[][] getCompanyTableData() throws ProcessingException;
}
