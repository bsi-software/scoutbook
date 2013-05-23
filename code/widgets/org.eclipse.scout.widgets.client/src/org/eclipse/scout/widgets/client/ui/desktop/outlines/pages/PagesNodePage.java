package org.eclipse.scout.widgets.client.ui.desktop.outlines.pages;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.shared.TEXTS;

public class PagesNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Pages");
  }
}
