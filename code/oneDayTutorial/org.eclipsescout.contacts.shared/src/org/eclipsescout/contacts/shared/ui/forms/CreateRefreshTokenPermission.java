package org.eclipsescout.contacts.shared.ui.forms;

import java.security.BasicPermission;

public class CreateRefreshTokenPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreateRefreshTokenPermission() {
    super("CreateRefreshToken");
  }
}
