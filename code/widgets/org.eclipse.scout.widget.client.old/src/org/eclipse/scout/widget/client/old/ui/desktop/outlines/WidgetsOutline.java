/**
 *
 */
package org.eclipse.scout.widget.client.old.ui.desktop.outlines;

import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widget.client.old.FormFieldsNodePage;
import org.eclipse.scout.widget.client.old.MenusNodePage;

/**
 * @author mzi
 */
public class WidgetsOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Widgets");
  }

  @Override
  protected void execCreateChildPages(List<IPage> pageList) throws ProcessingException {
    FormFieldsNodePage formFieldsNodePage = new FormFieldsNodePage();
    pageList.add(formFieldsNodePage);
    MenusNodePage menusNodePage = new MenusNodePage();
    pageList.add(menusNodePage);
  }
}
