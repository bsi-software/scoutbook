package org.eclipse.scout.widgets.ui.rap;

import org.eclipse.scout.rt.ui.rap.mobile.AbstractMobileStandaloneRwtEnvironment;
import org.eclipse.scout.widgets.client.ClientSession;

public class MobileStandaloneRwtEnvironment extends AbstractMobileStandaloneRwtEnvironment {

  public MobileStandaloneRwtEnvironment() {
    super(Activator.getDefault().getBundle(), ClientSession.class);
  }
}
