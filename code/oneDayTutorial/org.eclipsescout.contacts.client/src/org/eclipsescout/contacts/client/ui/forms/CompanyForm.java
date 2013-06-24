package org.eclipsescout.contacts.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.shell.IShellService;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.contacts.client.ui.forms.CompanyForm.MainBox.CancelButton;
import org.eclipsescout.contacts.client.ui.forms.CompanyForm.MainBox.CompanyBox;
import org.eclipsescout.contacts.client.ui.forms.CompanyForm.MainBox.CompanyBox.LocationField;
import org.eclipsescout.contacts.client.ui.forms.CompanyForm.MainBox.CompanyBox.NameField;
import org.eclipsescout.contacts.client.ui.forms.CompanyForm.MainBox.CompanyBox.URLField;
import org.eclipsescout.contacts.client.ui.forms.CompanyForm.MainBox.OkButton;
import org.eclipsescout.contacts.client.ui.forms.CompanyForm.MainBox.OpenCompanyURLInBrowserButton;
import org.eclipsescout.contacts.shared.ui.forms.CompanyFormData;
import org.eclipsescout.contacts.shared.ui.forms.ICompanyService;
import org.eclipsescout.contacts.shared.ui.forms.UpdateCompanyPermission;

@FormData(value = CompanyFormData.class, sdkCommand = SdkCommand.CREATE)
public class CompanyForm extends AbstractForm {

  private String m_companyId;

  public CompanyForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Company");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  public CompanyBox getCompanyBox() {
    return getFieldByClass(CompanyBox.class);
  }

  public LocationField getLocationField() {
    return getFieldByClass(LocationField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public NameField getNameField() {
    return getFieldByClass(NameField.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public OpenCompanyURLInBrowserButton getOpenCompanyURLInBrowserButton() {
    return getFieldByClass(OpenCompanyURLInBrowserButton.class);
  }

  public URLField getURLField() {
    return getFieldByClass(URLField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(10.0)
    public class CompanyBox extends AbstractGroupBox {

      @Order(10.0)
      public class NameField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Name");
        }
      }

      @Order(20.0)
      public class LocationField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Location");
        }
      }

      @Order(30.0)
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

    @Order(40.0)
    public class OpenCompanyURLInBrowserButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("OpenCompanyURL");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        SERVICES.getService(IShellService.class).shellOpen(getURLField().getValue());
      }
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      ICompanyService service = SERVICES.getService(ICompanyService.class);
      CompanyFormData formData = new CompanyFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateCompanyPermission());
    }

    @Override
    public void execStore() throws ProcessingException {
      ICompanyService service = SERVICES.getService(ICompanyService.class);
      CompanyFormData formData = new CompanyFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      ICompanyService service = SERVICES.getService(ICompanyService.class);
      CompanyFormData formData = new CompanyFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);
    }

    @Override
    public void execStore() throws ProcessingException {
      ICompanyService service = SERVICES.getService(ICompanyService.class);
      CompanyFormData formData = new CompanyFormData();
      exportFormData(formData);
      formData = service.create(formData);
    }
  }

  @FormData
  public String getCompanyId() {
    return m_companyId;
  }

  @FormData
  public void setCompanyId(String companyId) {
    m_companyId = companyId;
  }
}
