package org.eclipse.scout.widgets.ui.rap;

import org.eclipse.scout.rt.ui.rap.mobile.AbstractTabletStandaloneRwtEnvironment;
import org.eclipse.scout.widgets.client.ClientSession;

public class TabletStandaloneRwtEnvironment extends AbstractTabletStandaloneRwtEnvironment {

  public TabletStandaloneRwtEnvironment() {
    super(Activator.getDefault().getBundle(), ClientSession.class);
  }
}
