package org.eclipsescout.contacts.client.ui.forms;

import java.net.URL;
import java.net.URLEncoder;

import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.ProcessingStatus;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagebox.AbstractImageField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipsescout.contacts.client.ui.forms.MapForm.MainBox.MapField;
import org.eclipsescout.contacts.client.ui.forms.MapForm.MainBox.OkButton;

public class MapForm extends AbstractForm {

  private String m_address;

  public MapForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Map");
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MapField getMapField() {
    return getFieldByClass(MapField.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(20.0)
    public class MapField extends AbstractImageField {

      @Override
      protected int getConfiguredGridH() {
        return 6;
      }

      @Override
      protected int getConfiguredHeightInPixel() {
        return 400;
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected int getConfiguredWidthInPixel() {
        return 400;
      }

      @Override
      protected void execInitField() throws ProcessingException {
        String address = StringUtility.nvl(getAddress(), "");
        String size = "" + getConfiguredHeightInPixel() + "x" + getConfiguredWidthInPixel();
        String url = null;

        try {
          url = "http://maps.googleapis.com/maps/api/staticmap?center=" +
              URLEncoder.encode(address, "ISO-8859-1") +
              "&zoom=13&size=" + size + "&maptype=roadmap&sensor=false";
          setImage(IOUtility.getContent((new URL(url)).openStream()));
        }
        catch (Exception e) {
          setErrorStatus(new ProcessingStatus("Bad Link: '" + url + "', please check",
              ProcessingStatus.ERROR));
          setImage(null);
          e.printStackTrace();
        }
      }
    }

    @Order(30.0)
    public class OkButton extends AbstractOkButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {
  }

  @FormData
  public String getAddress() {
    return m_address;
  }

  @FormData
  public void setAddress(String address) {
    m_address = address;
  }
}
