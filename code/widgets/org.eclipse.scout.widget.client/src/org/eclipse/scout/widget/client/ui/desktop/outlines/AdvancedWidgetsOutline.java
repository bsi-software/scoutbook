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
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm;
import org.eclipse.scout.widget.client.ui.forms.SvgFieldForm;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm;
import org.eclipse.scout.widget.client.ui.forms.TreeFieldForm;

/**
 * @author mzi
 */
public class AdvancedWidgetsOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("AdvancedWidgets");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {

    FormPage listBoxFieldPage = new FormPage(ListBoxForm.class);
    pageList.add(listBoxFieldPage);

    FormPage treeBoxPage = new FormPage(TreeBoxForm.class);
    pageList.add(treeBoxPage);

    FormPage smartFieldPage = new FormPage(SmartFieldForm.class);
    pageList.add(smartFieldPage);

    FormPage treeFieldPage = new FormPage(TreeFieldForm.class);
    pageList.add(treeFieldPage);

    FormPage tableFieldPage = new FormPage(TableFieldForm.class);
    pageList.add(tableFieldPage);

    FormPage imageFieldPage = new FormPage(ImageFieldForm.class);
    pageList.add(imageFieldPage);

    FormPage svgFieldPage = new FormPage(SvgFieldForm.class);
    pageList.add(svgFieldPage);
  }
}
