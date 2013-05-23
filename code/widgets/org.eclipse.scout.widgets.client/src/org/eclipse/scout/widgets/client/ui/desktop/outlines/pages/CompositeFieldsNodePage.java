package org.eclipse.scout.widgets.client.ui.desktop.outlines.pages;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widgets.shared.Icons;

public class CompositeFieldsNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredIconId() {
    return Icons.Forms;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("CompositeFields");
  }
}
