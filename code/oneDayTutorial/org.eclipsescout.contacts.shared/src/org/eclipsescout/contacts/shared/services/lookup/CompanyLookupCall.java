package org.eclipsescout.contacts.shared.services.lookup;

import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipsescout.contacts.shared.services.lookup.ICompanyLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

public class CompanyLookupCall extends LookupCall{

  private static final long serialVersionUID = 1L;

  @Override
  protected Class<? extends ILookupService> getConfiguredService() {
    return ICompanyLookupService.class;
  }
}
