package org.eclipse.scout.widgets.client.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widgets.client.ui.desktop.outlines.pages.AllFieldsNodePage;
import org.eclipse.scout.widgets.client.ui.desktop.outlines.pages.CompositeFieldsNodePage;
import org.eclipse.scout.widgets.client.ui.desktop.outlines.pages.SpecialFieldsNodePage;
import org.eclipse.scout.widgets.client.ui.desktop.outlines.pages.ValueFieldsNodePage;

public class FormFieldOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("FormFields");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    pageList.add(new AllFieldsNodePage());
    pageList.add(new ValueFieldsNodePage());
    pageList.add(new CompositeFieldsNodePage());
    pageList.add(new SpecialFieldsNodePage());
  }
}
