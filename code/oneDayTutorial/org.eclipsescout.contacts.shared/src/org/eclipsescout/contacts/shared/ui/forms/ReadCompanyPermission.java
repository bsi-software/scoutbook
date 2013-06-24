package org.eclipsescout.contacts.shared.ui.forms;

import java.security.BasicPermission;

public class ReadCompanyPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadCompanyPermission() {
    super("ReadCompany");
  }
}
