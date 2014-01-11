/*******************************************************************************
 * Copyright (c) 2013 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.widget.client.ui.forms;

import java.io.FileInputStream;
import java.net.URL;

import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.dnd.FileListTransferObject;
import org.eclipse.scout.commons.dnd.TransferObject;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagebox.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.ConfigurationBox.AutoFitField;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.ConfigurationBox.HorizontalAlignmentField;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.ConfigurationBox.ImageField;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.ConfigurationBox.ImageURLField;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.ConfigurationBox.ScrollbarEnabledField;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.ConfigurationBox.VerticalAlignmentField;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.ExamplesBox;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.ExamplesBox.DefaultField;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.ExamplesBox.IconContentField;
import org.eclipse.scout.widget.client.ui.forms.ImageFieldForm.MainBox.SampleContentButton;

public class ImageFieldForm extends AbstractForm implements IPageForm {

  public ImageFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ImageField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  /**
   * @return the AutoFitField
   */
  public AutoFitField getAutoFitField() {
    return getFieldByClass(AutoFitField.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  /**
   * @return the DefaultField
   */
  public DefaultField getDefaultField() {
    return getFieldByClass(DefaultField.class);
  }

  public ExamplesBox getExamplesBox() {
    return getFieldByClass(ExamplesBox.class);
  }

  public ConfigurationBox getConfigurationBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  /**
   * @return the HorizontalAlignmentField
   */
  public HorizontalAlignmentField getHorizontalAlignmentField() {
    return getFieldByClass(HorizontalAlignmentField.class);
  }

  /**
   * @return the IconContentField
   */
  public IconContentField getIconContentField() {
    return getFieldByClass(IconContentField.class);
  }

  /**
   * @return the ImageField
   */
  public ImageField getImageField() {
    return getFieldByClass(ImageField.class);
  }

  /**
   * @return the ImageURLField
   */
  public ImageURLField getImageURLField() {
    return getFieldByClass(ImageURLField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the SampleContentButton
   */
  public SampleContentButton getSampleContentButton() {
    return getFieldByClass(SampleContentButton.class);
  }

  /**
   * @return the ScrollbarEnabledField
   */
  public ScrollbarEnabledField getScrollbarEnabledField() {
    return getFieldByClass(ScrollbarEnabledField.class);
  }

  /**
   * @return the VerticalAlignmentField
   */
  public VerticalAlignmentField getVerticalAlignmentField() {
    return getFieldByClass(VerticalAlignmentField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    public static final String SCOUT_LOGO = "http://wiki.eclipse.org/images/e/eb/ScoutIconLarge.gif";

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(10.0)
    public class ExamplesBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Examples");
      }

      @Order(10.0)
      public class DefaultField extends AbstractImageField {

        @Override
        protected int getConfiguredDropType() {
          return TYPE_IMAGE_TRANSFER;
        }

        @Override
        protected int getConfiguredGridH() {
          return 3;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }

        @Override
        protected void execDropRequest(TransferObject transferObject) throws ProcessingException {
          clearErrorStatus();

          if (transferObject instanceof FileListTransferObject) {
            FileListTransferObject to = (FileListTransferObject) transferObject;
            String imageName = to.getFilenames()[0];
            try {
              setImage(IOUtility.getContent(new FileInputStream(imageName)));
              setAutoFit(true);
            }
            catch (Exception e) {
              e.printStackTrace();
              setErrorStatus(e.getMessage());
            }
          }
        }

        @Override
        protected void execInitField() throws ProcessingException {
          clearErrorStatus();
          try {
            URL url = new URL(SCOUT_LOGO);
            setImage(IOUtility.getContent(url.openStream()));
          }
          catch (Exception e) {
            e.printStackTrace();
            setErrorStatus(e.getMessage());
          }
        }
      }

      @Order(20.0)
      public class IconContentField extends AbstractImageField {

        @Override
        protected int getConfiguredHorizontalAlignment() {
          return -1;
        }

        @Override
        protected String getConfiguredImageId() {
          return AbstractIcons.Bookmark;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("IconImage");
        }
      }
    }

    @Order(20.0)
    public class ConfigurationBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Configure");
      }

      @Order(10.0)
      public class ImageField extends AbstractImageField {

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ImageField");
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return ImageFieldForm.MainBox.ConfigurationBox.ImageURLField.class;
        }

        @Override
        protected boolean getConfiguredScrollBarEnabled() {
          return true;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          getImageURLField().clearErrorStatus();

          try {
            URL url = new URL((String) newMasterValue);
            setImage(IOUtility.getContent(url.openStream()));
          }
          catch (Exception e) {
            e.printStackTrace();
            getImageURLField().setErrorStatus(e.getMessage());
          }
        }
      }

      @Order(20.0)
      public class ImageURLField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ImageURL");
        }
      }

      @Order(30.0)
      public class HorizontalAlignmentField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("HorizontalAlignment");
        }

        @Override
        protected Integer getConfiguredMaxValue() {
          return 1;
        }

        @Override
        protected Integer getConfiguredMinValue() {
          return -1;
        }
      }

      @Order(40.0)
      public class VerticalAlignmentField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("VerticalAlignment");
        }

        @Override
        protected Integer getConfiguredMaxValue() {
          return 1;
        }

        @Override
        protected Integer getConfiguredMinValue() {
          return -1;
        }
      }

      @Order(50.0)
      public class AutoFitField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("AutoFit");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getImageField().setAutoFit(getValue());
        }
      }

      @Order(60.0)
      public class ScrollbarEnabledField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ScrollbarEnabled");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getImageField().setScrollBarEnabled(getValue());
        }
      }
    }

    @Order(30.0)
    public class SampleContentButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("SampleContent");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getImageURLField().setValue(SCOUT_LOGO);
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
