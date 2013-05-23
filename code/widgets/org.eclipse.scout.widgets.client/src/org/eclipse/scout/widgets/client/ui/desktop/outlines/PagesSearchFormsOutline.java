package org.eclipse.scout.widgets.client.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widgets.client.ui.desktop.outlines.pages.PagesNodePage;
import org.eclipse.scout.widgets.client.ui.desktop.outlines.pages.SearchFormsNodePage;

public class PagesSearchFormsOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PagesSearchForms");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    PagesNodePage pagesNodePage = new PagesNodePage();
    pageList.add(pagesNodePage);
pageList.add(new SearchFormsNodePage());

  }

}
