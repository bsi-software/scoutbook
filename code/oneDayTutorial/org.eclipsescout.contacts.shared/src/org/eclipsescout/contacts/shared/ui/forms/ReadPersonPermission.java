package org.eclipsescout.contacts.shared.ui.forms;

import java.security.BasicPermission;

public class ReadPersonPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadPersonPermission() {
    super("ReadPerson");
  }
}
