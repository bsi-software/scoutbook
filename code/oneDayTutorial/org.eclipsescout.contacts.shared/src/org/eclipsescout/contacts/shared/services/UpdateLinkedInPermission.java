package org.eclipsescout.contacts.shared.services;

import java.security.BasicPermission;

public class UpdateLinkedInPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public UpdateLinkedInPermission() {
  super("UpdateLinkedIn");
  }
}
