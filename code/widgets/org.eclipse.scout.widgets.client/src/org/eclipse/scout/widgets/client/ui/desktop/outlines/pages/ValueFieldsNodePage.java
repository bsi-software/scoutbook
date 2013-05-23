package org.eclipse.scout.widgets.client.ui.desktop.outlines.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widgets.client.ui.forms.StringFieldForm;
import org.eclipse.scout.widgets.shared.Icons;

public class ValueFieldsNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredIconId() {
    return Icons.Forms;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ValueFields");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    pageList.add(new FormPage(StringFieldForm.class));
  }
}
