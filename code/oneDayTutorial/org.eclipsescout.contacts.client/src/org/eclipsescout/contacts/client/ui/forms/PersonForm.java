package org.eclipsescout.contacts.client.ui.forms;

import java.net.URL;

import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagebox.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.CancelButton;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.DetailBox;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.DetailBox.CompanyField;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.DetailBox.DateOfBirthField;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.DetailBox.HeadlineField;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.DetailBox.LocationField;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.OkButton;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.PersonBox;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.PersonBox.FirstNameField;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.PersonBox.LastNameField;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.PersonBox.PictureField;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.PersonBox.PictureUrlField;
import org.eclipsescout.contacts.client.ui.forms.PersonForm.MainBox.ShowMapButton;
import org.eclipsescout.contacts.shared.Icons;
import org.eclipsescout.contacts.shared.services.lookup.CompanyLookupCall;
import org.eclipsescout.contacts.shared.ui.forms.IPersonService;
import org.eclipsescout.contacts.shared.ui.forms.PersonFormData;
import org.eclipsescout.contacts.shared.ui.forms.UpdatePersonPermission;

@FormData(value = PersonFormData.class, sdkCommand = SdkCommand.CREATE)
public class PersonForm extends AbstractForm {

  private String m_personId;

  public PersonForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Eye;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Person");
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

  public CompanyField getCompanyField() {
    return getFieldByClass(CompanyField.class);
  }

  public DateOfBirthField getDateOfBirthField() {
    return getFieldByClass(DateOfBirthField.class);
  }

  public DetailBox getDetailBox() {
    return getFieldByClass(DetailBox.class);
  }

  public FirstNameField getFirstNameField() {
    return getFieldByClass(FirstNameField.class);
  }

  public HeadlineField getHeadlineField() {
    return getFieldByClass(HeadlineField.class);
  }

  public LastNameField getLastNameField() {
    return getFieldByClass(LastNameField.class);
  }

  public LocationField getLocationField() {
    return getFieldByClass(LocationField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public PersonBox getPersonBox() {
    return getFieldByClass(PersonBox.class);
  }

  public PictureField getPictureField() {
    return getFieldByClass(PictureField.class);
  }

  public PictureUrlField getPictureUrlField() {
    return getFieldByClass(PictureUrlField.class);
  }

  public ShowMapButton getShowMapButton() {
    return getFieldByClass(ShowMapButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class PersonBox extends AbstractGroupBox {

      @Order(40.0)
      public class PictureField extends AbstractImageField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Picture");
        }

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return PictureUrlField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          try {
            URL url = new URL((String) newMasterValue);
            setImage(IOUtility.getContent(url.openStream()));
            setAutoFit(true);
          }
          catch (Exception e) {
            e.printStackTrace();
          }
        }

        @Order(10.0)
        public class EditURLMenu extends AbstractExtensibleMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("EditURL");
          }

          @Override
          protected void execAction() throws ProcessingException {
            PictureURLForm form = new PictureURLForm();
            form.startModify();
            form.waitFor();
            if (form.isFormStored()) {
              getPictureUrlField().setValue(form.getURLField().getValue());
            }
          }
        }
      }

      @Order(10.0)
      public class FirstNameField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("FirstName");
        }
      }

      @Order(20.0)
      public class LastNameField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("LastName");
        }
      }

      @Order(30.0)
      public class PictureUrlField extends AbstractStringField {

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
      }
    }

    @Order(20.0)
    public class DetailBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Detail");
      }

      @Order(10.0)
      public class HeadlineField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Headline");
        }
      }

      @Order(20.0)
      public class CompanyField extends AbstractSmartField<String> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Company");
        }

        @Override
        protected Class<? extends LookupCall> getConfiguredLookupCall() {
          return CompanyLookupCall.class;

        }
      }

      @Order(30.0)
      public class LocationField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Location");
        }
      }

      @Order(40.0)
      public class DateOfBirthField extends AbstractDateField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DateOfBirth");
        }
      }
    }

    @Order(30.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(40.0)
    public class CancelButton extends AbstractCancelButton {
    }

    @Order(50.0)
    public class ShowMapButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("ShowMap");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        MapForm mapForm = new MapForm();
        mapForm.setAddress(getLocationField().getValue());
        mapForm.startModify();
      }
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IPersonService service = SERVICES.getService(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdatePersonPermission());
    }

    @Override
    public void execStore() throws ProcessingException {
      IPersonService service = SERVICES.getService(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IPersonService service = SERVICES.getService(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);
    }

    @Override
    public void execStore() throws ProcessingException {
      IPersonService service = SERVICES.getService(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.create(formData);
    }
  }

  @FormData
  public String getPersonId() {
    return m_personId;
  }

  @FormData
  public void setPersonId(String personId) {
    m_personId = personId;
  }
}
