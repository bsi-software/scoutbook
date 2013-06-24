package org.eclipsescout.contacts.shared.ui.forms;

import java.security.BasicPermission;

public class UpdateCompanyPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateCompanyPermission() {
    super("UpdateCompany");
  }
}
