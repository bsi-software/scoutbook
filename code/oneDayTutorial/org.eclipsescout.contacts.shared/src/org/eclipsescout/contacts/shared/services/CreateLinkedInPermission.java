package org.eclipsescout.contacts.shared.services;

import java.security.BasicPermission;

public class CreateLinkedInPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public CreateLinkedInPermission() {
  super("CreateLinkedIn");
  }
}
