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
import org.eclipse.scout.widget.client.ui.forms.ButtonLinkFieldsForm;
import org.eclipse.scout.widget.client.ui.forms.CheckboxFieldForm;
import org.eclipse.scout.widget.client.ui.forms.DateTimeFieldsForm;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm;
import org.eclipse.scout.widget.client.ui.forms.LabelFieldForm;
import org.eclipse.scout.widget.client.ui.forms.MessageBoxForm;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm;
import org.eclipse.scout.widget.client.ui.forms.RadioButtonGroupFieldForm;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm;

/**
 * @author mzi
 */
public class SimpleWidgetsOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("SimpleWidgets");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {

    FormPage labelFieldPage = new FormPage(LabelFieldForm.class);
    pageList.add(labelFieldPage);

    FormPage stringFieldPage = new FormPage(StringFieldForm.class);
    pageList.add(stringFieldPage);

    FormPage numberFieldPage = new FormPage(NumberFieldsForm.class);
    pageList.add(numberFieldPage);

    FormPage decimalFieldPage = new FormPage(DecimalFieldsForm.class);
    pageList.add(decimalFieldPage);

    FormPage dateTimeFieldPage = new FormPage(DateTimeFieldsForm.class);
    pageList.add(dateTimeFieldPage);

    FormPage checkboxFieldPage = new FormPage(CheckboxFieldForm.class);
    pageList.add(checkboxFieldPage);

    FormPage radioButtonFieldPage = new FormPage(RadioButtonGroupFieldForm.class);
    pageList.add(radioButtonFieldPage);

    FormPage buttonLinkFieldsPage = new FormPage(ButtonLinkFieldsForm.class);
    pageList.add(buttonLinkFieldsPage);

    FormPage messageBoxPage = new FormPage(MessageBoxForm.class);
    pageList.add(messageBoxPage);
  }
}
