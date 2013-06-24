package org.eclipsescout.contacts.shared.services;

import java.security.BasicPermission;

public class ReadLinkedInPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public ReadLinkedInPermission() {
  super("ReadLinkedIn");
  }
}
