package org.eclipsescout.contacts.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipsescout.contacts.client.ui.forms.PictureURLForm.MainBox.CancelButton;
import org.eclipsescout.contacts.client.ui.forms.PictureURLForm.MainBox.OkButton;
import org.eclipsescout.contacts.client.ui.forms.PictureURLForm.MainBox.UrlBox;
import org.eclipsescout.contacts.client.ui.forms.PictureURLForm.MainBox.UrlBox.URLField;

public class PictureURLForm extends AbstractForm {

  public PictureURLForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PictureURL");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public URLField getURLField() {
    return getFieldByClass(URLField.class);
  }

  public UrlBox getUrlBox() {
    return getFieldByClass(UrlBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class UrlBox extends AbstractGroupBox {

      @Order(10.0)
      public class URLField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("URL");
        }
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {
  }
}
