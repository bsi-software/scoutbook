/**
 *
 */
package org.eclipse.scout.transport.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.service.SERVICES;
import org.eclipse.scout.transport.client.services.lookup.EventTypeLookupCall;
import org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox;
import org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox.DatesBox;
import org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox.DatesBox.ShowDatesFrom;
import org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox.DatesBox.ShowDatesTo;
import org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox.EventField;
import org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox.EventTypeField;
import org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox.GetValueField;
import org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox.SetValueBox;
import org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox.SetValueBox.SetValueButton;
import org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox.SetValueBox.ValueField;
import org.eclipse.scout.transport.shared.Icons;
import org.eclipse.scout.transport.shared.services.DesktopFormData;
import org.eclipse.scout.transport.shared.services.IDesktopService;
import org.eclipse.scout.transport.shared.services.lookup.EventLookupCall;

/**
 * @author mzi
 */
@FormData(value = DesktopFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class DesktopForm extends AbstractForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public DesktopForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.EclipseScout;
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }

  /**
   * @return the EventField
   */
  public EventField getEventField() {
    return getFieldByClass(EventField.class);
  }

  /**
   * @return the DesktopBox
   */
  public DesktopBox getDesktopBox() {
    return getFieldByClass(DesktopBox.class);
  }

  /**
   * @return the EventTypeField
   */
  public EventTypeField getEventTypeField() {
    return getFieldByClass(EventTypeField.class);
  }

  /**
   * @return the GetValueField
   */
  public GetValueField getGetValueField() {
    return getFieldByClass(GetValueField.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the DatesBox
   */
  public DatesBox getDatesBox() {
    return getFieldByClass(DatesBox.class);
  }

  /**
   * @return the SetValueBox
   */
  public SetValueBox getSetValueBox() {
    return getFieldByClass(SetValueBox.class);
  }

  /**
   * @return the SetValueButton
   */
  public SetValueButton getSetValueButton() {
    return getFieldByClass(SetValueButton.class);
  }

  /**
   * @return the ShowDatesFrom
   */
  public ShowDatesFrom getShowDatesFrom() {
    return getFieldByClass(ShowDatesFrom.class);
  }

  /**
   * @return the ShowDatesTo
   */
  public ShowDatesTo getShowDatesTo() {
    return getFieldByClass(ShowDatesTo.class);
  }

  /**
   * @return the ValueField
   */
  public ValueField getValueField() {
    return getFieldByClass(ValueField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class DesktopBox extends AbstractGroupBox {

      @Override
      protected boolean getConfiguredEnabled() {
        return false;
      }

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Order(10.0)
      public class EventTypeField extends AbstractSmartField<Long> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TypeOfEvent");
        }

        @Override
        protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
          return EventTypeLookupCall.class;
        }
      }

      @Order(20.0)
      public class DatesBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Dates");
        }

        @Order(10.0)
        public class ShowDatesFrom extends AbstractDateField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("from");
          }
        }

        @Order(20.0)
        public class ShowDatesTo extends AbstractDateField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("to");
          }
        }
      }

      @Order(30.0)
      public class EventField extends AbstractSmartField<String> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Event");
        }

        @Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return EventLookupCall.class;
        }

        @Override
        protected void execPrepareLookup(ILookupCall<String> call) throws ProcessingException {
          EventLookupCall lc = (EventLookupCall) call;
          lc.setFromDate(getShowDatesFrom().getValue());
          lc.setToDate(getShowDatesTo().getValue());
          lc.setMaster(getEventTypeField().getValue());
        }
      }

      @Order(40.0)
      public class GetValueField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("getValue");
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return DesktopForm.MainBox.DesktopBox.EventField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          setValue((String) newMasterValue);
        }
      }

      @Order(50.0)
      public class SetValueBox extends AbstractSequenceBox {

        @Order(10.0)
        public class ValueField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Value");
          }
        }

        @Order(20.0)
        public class SetValueButton extends AbstractButton {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("SetValue");
          }

          @Override
          protected void execClickAction() throws ProcessingException {
            getEventField().setValue(getValueField().getValue());
          }
        }
      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IDesktopService service = SERVICES.getService(IDesktopService.class);
      DesktopFormData formData = new DesktopFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);

    }
  }
}
