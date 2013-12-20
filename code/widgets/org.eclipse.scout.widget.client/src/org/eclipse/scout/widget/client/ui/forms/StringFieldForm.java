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

import org.eclipse.scout.commons.NumberUtility;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.placeholder.AbstractPlaceholderField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ConfigurationBox.InputMaskedField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ConfigurationBox.MaxLengthField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ConfigurationBox.PlaceholderField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ConfigurationBox.StringInputField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ConfigurationBox.TextInputField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ConfigurationBox.UpperCaseField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ConfigurationBox.WrapTextField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ExamplesBox;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ExamplesBox.DefaultField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ExamplesBox.DisabledField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ExamplesBox.MandatoryField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ExamplesBox.StyledField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.LoremIpsumButton;

public class StringFieldForm extends AbstractForm implements IPageForm {

  public StringFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("StringField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public ExamplesBox getExamplesBox() {
    return getFieldByClass(ExamplesBox.class);
  }

  public DefaultField getDefaultField() {
    return getFieldByClass(DefaultField.class);
  }

  public DisabledField getDisabledField() {
    return getFieldByClass(DisabledField.class);
  }

  public MaxLengthField getMaxLengthField() {
    return getFieldByClass(MaxLengthField.class);
  }

  public PlaceholderField getPlaceholderField() {
    return getFieldByClass(PlaceholderField.class);
  }

  public ConfigurationBox getConfigurationBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  public InputMaskedField getInputMaskedField() {
    return getFieldByClass(InputMaskedField.class);
  }

  public LoremIpsumButton getLoremIpsumButton() {
    return getFieldByClass(LoremIpsumButton.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MandatoryField getMandatoryField() {
    return getFieldByClass(MandatoryField.class);
  }

  public StringInputField getStringInputField() {
    return getFieldByClass(StringInputField.class);
  }

  public StyledField getStyledField() {
    return getFieldByClass(StyledField.class);
  }

  public TextInputField getTextInputField() {
    return getFieldByClass(TextInputField.class);
  }

  public UpperCaseField getUpperCaseField() {
    return getFieldByClass(UpperCaseField.class);
  }

  public WrapTextField getWrapTextField() {
    return getFieldByClass(WrapTextField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

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
      public class DefaultField extends AbstractStringField {

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }
      }

      @Order(20.0)
      public class MandatoryField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }

        @Override
        protected String execValidateValue(String rawValue) throws ProcessingException {
          if (StringUtility.isNullOrEmpty(rawValue)) {
            throw new VetoException("Field content must not be empty");
          }
          return rawValue;
        }
      }

      @Order(30.0)
      public class DisabledField extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Disabled");
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue("Text in disabled Field");
        }
      }

      @Order(40.0)
      public class StyledField extends AbstractStringField {

        @Override
        protected String getConfiguredBackgroundColor() {
          return "FDFFAA";
        }

        @Override
        protected String getConfiguredFont() {
          return "BOLD";
        }

        @Override
        protected String getConfiguredForegroundColor() {
          return "0080C0";
        }

        @Override
        protected String getConfiguredFormat() {
          return "j";
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Styled");
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
      public class StringInputField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("StringInput");
        }
      }

      @Order(20.0)
      public class MaxLengthField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MaxLength");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getStringInputField().setMaxLength(NumberUtility.nvl(getValue(), 4000));
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(5);
          getStringInputField().setMaxLength(5);
        }
      }

      @Order(30.0)
      public class PlaceholderField extends AbstractPlaceholderField {
      }

      @Order(40.0)
      public class UpperCaseField extends AbstractStringField {

        @Override
        protected boolean getConfiguredFormatUpper() {
          return true;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("UpperCase");
        }
      }

      @Order(50.0)
      public class InputMaskedField extends AbstractStringField {

        @Override
        protected boolean getConfiguredInputMasked() {
          return true;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("InputMasked");
        }
      }

      @Order(60.0)
      public class TextInputField extends AbstractStringField {

        @Override
        protected int getConfiguredGridH() {
          return 4;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MultilineText");
        }

        @Override
        protected boolean getConfiguredMultilineText() {
          return true;
        }
      }

      @Order(70.0)
      public class WrapTextField extends AbstractCheckBox {

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("WrapText");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getTextInputField().setWrapText(getValue());
        }
      }
    }

    @Order(30.0)
    public class LoremIpsumButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("LoremIpsumButton");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getTextInputField().setValue(TEXTS.get("Lorem") + "\n" + TEXTS.get("Lorem"));
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
