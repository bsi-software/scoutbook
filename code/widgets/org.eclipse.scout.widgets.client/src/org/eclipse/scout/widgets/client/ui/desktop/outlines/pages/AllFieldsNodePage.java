package org.eclipse.scout.widgets.client.ui.desktop.outlines.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widgets.client.ui.forms.FormFieldTreeForm;
import org.eclipse.scout.widgets.client.ui.forms.StringFieldForm;
import org.eclipse.scout.widgets.client.ui.forms.TableFieldForm;
import org.eclipse.scout.widgets.shared.Icons;

public class AllFieldsNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredIconId() {
    return Icons.Forms;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("AllFields");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    pageList.add(new FormPage(StringFieldForm.class));
    pageList.add(new FormPage(TableFieldForm.class));
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    FormFieldTreeForm form = new FormFieldTreeForm(this);
    setDetailForm(form);
    form.startPageForm();
    setTableVisible(false);
  }
}
