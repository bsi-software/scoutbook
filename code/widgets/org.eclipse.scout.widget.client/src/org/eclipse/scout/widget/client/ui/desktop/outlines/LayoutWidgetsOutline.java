/**
 *
 */
package org.eclipse.scout.widget.client.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widget.client.ui.desktop.pages.FormPage;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm;
import org.eclipse.scout.widget.client.ui.forms.SequenceBoxForm;
import org.eclipse.scout.widget.client.ui.forms.SplitBoxForm;
import org.eclipse.scout.widget.client.ui.forms.TabBoxForm;

/**
 * @author mzi
 */
public class LayoutWidgetsOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("LayoutWidgets");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {

    FormPage groupBoxPage = new FormPage(GroupBoxForm.class);
    pageList.add(groupBoxPage);

    FormPage sequenceBoxPage = new FormPage(SequenceBoxForm.class);
    pageList.add(sequenceBoxPage);

    FormPage tabBoxPage = new FormPage(TabBoxForm.class);
    pageList.add(tabBoxPage);

    FormPage splitBoxPage = new FormPage(SplitBoxForm.class);
    pageList.add(splitBoxPage);
  }
}
