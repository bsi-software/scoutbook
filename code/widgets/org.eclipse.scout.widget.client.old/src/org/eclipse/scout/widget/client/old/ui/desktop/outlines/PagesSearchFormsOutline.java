/**
 *
 */
package org.eclipse.scout.widget.client.old.ui.desktop.outlines;

import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widget.client.old.PagesNodePage;
import org.eclipse.scout.widget.client.old.SearchFormsNodePage;

/**
 * @author mzi
 */
public class PagesSearchFormsOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PagesSearchForms");
  }

  @Override
  protected void execCreateChildPages(List<IPage> pageList) throws ProcessingException {
    PagesNodePage pagesNodePage = new PagesNodePage();
    pageList.add(pagesNodePage);
    SearchFormsNodePage searchFormsNodePage = new SearchFormsNodePage();
    pageList.add(searchFormsNodePage);
  }
}
