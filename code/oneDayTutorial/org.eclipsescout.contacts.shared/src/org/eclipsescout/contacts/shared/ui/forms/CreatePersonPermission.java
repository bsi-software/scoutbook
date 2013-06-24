package org.eclipsescout.contacts.shared.ui.forms;

import java.security.BasicPermission;

public class CreatePersonPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreatePersonPermission() {
    super("CreatePerson");
  }
}
