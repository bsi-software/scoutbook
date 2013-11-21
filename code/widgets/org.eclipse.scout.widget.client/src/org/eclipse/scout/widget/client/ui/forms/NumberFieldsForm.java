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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParsePosition;

import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.bigintegerfield.AbstractBigIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.longfield.AbstractLongField;
import org.eclipse.scout.rt.client.ui.form.fields.placeholder.AbstractPlaceholderField;
import org.eclipse.scout.rt.shared.ScoutTexts;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.BigDecimalField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.DoubleField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.CloseButton;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.BigIntegerInputField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.GroupingField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.InputField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.LongInputField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.MaximumValueField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.MinimumValueField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.Place1Field;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.Place2Field;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.Place3Field;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.Place4Field;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ConfigurationBox.Place5Field;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.BigIntDisabledField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.BigIntMandatoryField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.BigIntStyledField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.BigIntegerColumnField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.BigIntegerField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.DisabledField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.IntegerColumnField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.IntegerField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.LongColumnField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.LongDisabledField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.LongField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.LongMandatoryField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.LongStyledField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.MandatoryField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.StyledField;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.HighestValueButton;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.SmallestValueButton;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ConfigurationBox.PlaceholderField;

public class NumberFieldsForm extends AbstractForm implements IPageForm {

  private static final long MIN_VALUE = 0;
  private static final long MAX_VALUE = 1000;

  public NumberFieldsForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("NumberFields");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public BigDecimalField getBigDecimalField() {
    return getFieldByClass(BigDecimalField.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public DisabledField getDisabledField() {
    return getFieldByClass(DisabledField.class);
  }

  public DoubleField getDoubleField() {
    return getFieldByClass(DoubleField.class);
  }

  public GroupingField getGroupingField() {
    return getFieldByClass(GroupingField.class);
  }

  public HighestValueButton getHighestValueButton() {
    return getFieldByClass(HighestValueButton.class);
  }

  public InputField getInputField() {
    return getFieldByClass(InputField.class);
  }

  public IntegerColumnField getIntegerColumnField() {
    return getFieldByClass(IntegerColumnField.class);
  }

  public IntegerField getIntegerField() {
    return getFieldByClass(IntegerField.class);
  }

  public LongColumnField getLongColumnField() {
    return getFieldByClass(LongColumnField.class);
  }

  public LongDisabledField getLongDisabledField() {
    return getFieldByClass(LongDisabledField.class);
  }

  public LongField getLongField() {
    return getFieldByClass(LongField.class);
  }

  public LongInputField getLongInputField() {
    return getFieldByClass(LongInputField.class);
  }

  public LongMandatoryField getLongMandatoryField() {
    return getFieldByClass(LongMandatoryField.class);
  }

  public LongStyledField getLongStyledField() {
    return getFieldByClass(LongStyledField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public BigIntDisabledField getBigIntDisabledField() {
    return getFieldByClass(BigIntDisabledField.class);
  }

  public BigIntMandatoryField getBigIntMandatoryField() {
    return getFieldByClass(BigIntMandatoryField.class);
  }

  public BigIntStyledField getBigIntStyledField() {
    return getFieldByClass(BigIntStyledField.class);
  }

  public BigIntegerColumnField getBigIntegerColumnField() {
    return getFieldByClass(BigIntegerColumnField.class);
  }

  public BigIntegerField getBigIntegerField() {
    return getFieldByClass(BigIntegerField.class);
  }

  public BigIntegerInputField getBigIntegerInputField() {
    return getFieldByClass(BigIntegerInputField.class);
  }

  public MandatoryField getMandatoryField() {
    return getFieldByClass(MandatoryField.class);
  }

  public MaximumValueField getMaximumValueField() {
    return getFieldByClass(MaximumValueField.class);
  }

  public MinimumValueField getMinimumValueField() {
    return getFieldByClass(MinimumValueField.class);
  }

  public ExamplesBox getExamplesBox() {
    return getFieldByClass(ExamplesBox.class);
  }

  public Place1Field getPlace1Field() {
    return getFieldByClass(Place1Field.class);
  }

  public Place2Field getPlace2Field() {
    return getFieldByClass(Place2Field.class);
  }

  public Place3Field getPlace3Field() {
    return getFieldByClass(Place3Field.class);
  }

  public Place4Field getPlace4Field() {
    return getFieldByClass(Place4Field.class);
  }

  public Place5Field getPlace5Field() {
    return getFieldByClass(Place5Field.class);
  }

  public PlaceholderField getPlaceholderField() {
    return getFieldByClass(PlaceholderField.class);
  }

  public SmallestValueButton getSmallestValueButton() {
    return getFieldByClass(SmallestValueButton.class);
  }

  public ConfigurationBox getConfigurationBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  public StyledField getStyledField() {
    return getFieldByClass(StyledField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class ExamplesBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 3;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Examples");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Order(10.0)
      public class IntegerColumnField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("IntegerField");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "BOLD";
        }
      }

      @Order(20.0)
      public class IntegerField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }

        /**
         * Parses the provided text and returns its integer representation.
         * If the provided text does not syntactically represent an integer number,
         * or does not fit into the range [Integer.MIN_VALUE .. Integer.MAX_VALUE],
         * an exception is thrown.
         */
        @Override
        protected Integer execParseValue(String text) throws ProcessingException {
          Integer retVal = null;
          text = StringUtility.nvl(text, "").trim();
          if (text.length() > 0) {
            DecimalFormat df = (DecimalFormat) createNumberFormat();
            df.setParseBigDecimal(true);
            df.setParseIntegerOnly(true);
            ParsePosition p = new ParsePosition(0);
            BigDecimal val = (BigDecimal) df.parse(text, p);
            // check for bad syntax
            if (p.getErrorIndex() >= 0 || p.getIndex() != text.length()) {
              throw new ProcessingException(ScoutTexts.get("InvalidNumberMessageX", text));
            }
            // check for bad range
            if (val.compareTo(BigDecimal.valueOf(Integer.MIN_VALUE)) < 0) {
              throw new ProcessingException(ScoutTexts.get("NumberTooSmallMessageXY", String.valueOf(Integer.MIN_VALUE), String.valueOf(Integer.MAX_VALUE)));
            }
            if (val.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) > 0) {
              throw new ProcessingException(ScoutTexts.get("NumberTooLargeMessageXY", String.valueOf(Integer.MIN_VALUE), String.valueOf(Integer.MAX_VALUE)));
            }
            retVal = val.intValueExact();
          }
          return retVal;
        }
      }

      @Order(30.0)
      public class MandatoryField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }

      @Order(40.0)
      public class DisabledField extends AbstractIntegerField {

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
          setValue(5);
        }
      }

      @Order(50.0)
      public class StyledField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Styled");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          if (getValue() < 0) {
            setForegroundColor("FF0000");
          }
          else {
            setForegroundColor(null);
          }
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(-3);
          setForegroundColor("FF0000");
        }
      }

      @Order(60.0)
      public class LongColumnField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("LongField");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "BOLD";
        }
      }

      @Order(70.0)
      public class LongField extends AbstractLongField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }

        @Override
        protected Long execParseValue(String text) throws ProcessingException {
          Long retVal = null;

          text = StringUtility.nvl(text, "");
          text = text.trim();

          if (text.length() > 0) {
            DecimalFormat df = (DecimalFormat) createNumberFormat();
            df.setParseBigDecimal(true);
            df.setParseIntegerOnly(true);
            ParsePosition p = new ParsePosition(0);
            BigDecimal val = (BigDecimal) df.parse(text, p);

            // check for bad syntax
            if (p.getErrorIndex() >= 0 || p.getIndex() != text.length()) {
              throw new ProcessingException(ScoutTexts.get("InvalidNumberMessageX", text));
            }

            // check for bad range
            if (val.compareTo(BigDecimal.valueOf(Long.MIN_VALUE)) < 0) {
              throw new ProcessingException(ScoutTexts.get("NumberTooSmallMessageXY", text));
            }
            if (val.compareTo(BigDecimal.valueOf(Long.MAX_VALUE)) > 0) {
              throw new ProcessingException(ScoutTexts.get("NumberTooLargeMessageXY", text));
            }

            retVal = val.longValueExact();
          }

          return retVal;
        }
      }

      @Order(80.0)
      public class LongMandatoryField extends AbstractLongField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }

      @Order(90.0)
      public class LongDisabledField extends AbstractLongField {

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
          setValue(5L);
        }
      }

      @Order(100.0)
      public class LongStyledField extends AbstractLongField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Styled");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          if (getValue() < 0) {
            setForegroundColor("FF0000");
          }
          else {
            setForegroundColor(null);
          }
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(-3L);
          setForegroundColor("FF0000");
        }
      }

      @Order(110.0)
      public class BigIntegerColumnField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BigIntegerField");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "BOLD";
        }
      }

      @Order(120.0)
      public class BigIntegerField extends AbstractBigIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BigIntegerField");
        }

        /**
         * Parses the provided text and returns its BigInteger representation.
         * If the provided text does not syntactically represent an integer number,
         * an exception is thrown.
         */
        @Override
        protected BigInteger execParseValue(String text) throws ProcessingException {
          BigInteger retVal = null;
          text = StringUtility.nvl(text, "").trim();
          if (text.length() > 0) {
            DecimalFormat df = (DecimalFormat) createNumberFormat();
            df.setParseBigDecimal(true);
            df.setParseIntegerOnly(true);
            ParsePosition p = new ParsePosition(0);
            BigDecimal val = (BigDecimal) df.parse(text, p);
            // check for bad syntax
            if (p.getErrorIndex() >= 0 || p.getIndex() != text.length()) {
              throw new ProcessingException(ScoutTexts.get("InvalidNumberMessageX", text));
            }
            retVal = ((BigDecimal) val).toBigInteger();
          }
          return retVal;
        }

        //        @Override
        //        protected BigInteger execParseValue(String text) throws ProcessingException {
        //          BigInteger retVal = null;
        //          if (text == null) {
        //            text = "";
        //          }
        //          else {
        //            text = text.trim();
        //          }
        //          if (text.length() > 0) {
        //            ParsePosition p = new ParsePosition(0);
        //            DecimalFormat df = (DecimalFormat) createNumberFormat();
        //            df.setParseBigDecimal(true);
        //            df.setParseIntegerOnly(true);
        //            Number n = df.parse(text, p);
        //
        //            if (p.getErrorIndex() >= 0 || p.getIndex() != text.length()) {
        //              throw new ProcessingException(ScoutTexts.get("InvalidNumberMessageX", text));
        //            }
        //
        //            retVal = ((BigDecimal) n).toBigInteger();
        //          }
        //
        //          return retVal;
        //        }
      }

      @Order(130.0)
      public class BigIntMandatoryField extends AbstractBigIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }

      @Order(140.0)
      public class BigIntDisabledField extends AbstractBigIntegerField {

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
          setValue(BigInteger.valueOf(5));
        }
      }

      @Order(150.0)
      public class BigIntStyledField extends AbstractBigIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Styled");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          if (getValue().signum() < 0) {
            setForegroundColor("FF0000");
          }
          else {
            setForegroundColor(null);
          }
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(BigInteger.valueOf(-3));
          setForegroundColor("FF0000");
        }
      }
    }

    @Order(20.0)
    public class ConfigurationBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 3;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Configure");
      }

      @Order(10.0)
      public class InputField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("IntegerFieldInput");
        }

        @Override
        protected Integer getConfiguredMinimumValue() {
          return (int) MIN_VALUE;
        }

        @Override
        protected Integer getConfiguredMaximumValue() {
          return (int) MAX_VALUE;
        }

        public void setGrouping(boolean grouping) {
          setGroupingUsed(grouping);
        }
      }

      @Order(20.0)
      public class MinimumValueField extends AbstractLongField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MinimumValue");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          if (getValue() != null) {
            getInputField().setMinValue(getValue().intValue());
            getLongInputField().setMinValue(getValue());
            getBigIntegerInputField().setMinValue(BigInteger.valueOf(getValue()));
          }
          else {
            getInputField().setMinValue(null);
            getLongInputField().setMinValue(null);
            getBigIntegerInputField().setMinValue(null);
          }

          getInputField().validateContent();
          getLongInputField().validateContent();
          getBigIntegerInputField().validateContent();
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(MIN_VALUE);
        }
      }

      @Order(30.0)
      public class MaximumValueField extends AbstractLongField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MaximumValue");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          if (getValue() != null) {
            getInputField().setMaxValue(getValue().intValue());
            getLongInputField().setMaxValue(getValue());
            getBigIntegerInputField().setMaxValue(BigInteger.valueOf(getValue()));
          }
          else {
            getInputField().setMaxValue(null);
            getLongInputField().setMaxValue(null);
            getBigIntegerInputField().setMaxValue(null);
          }

          getInputField().validateContent();
          getLongInputField().validateContent();
          getBigIntegerInputField().validateContent();
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(MAX_VALUE);
        }
      }

      @Order(40.0)
      public class GroupingField extends AbstractCheckBox {

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Grouping");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getInputField().setGrouping(getValue());
          getLongInputField().setGrouping(getValue());
          getBigIntegerInputField().setGrouping(getValue());
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(true);
        }
      }

      @Order(50.0)
      public class LongInputField extends AbstractLongField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("LongFieldInput");
        }

        @Override
        protected Long getConfiguredMaximumValue() {
          return MAX_VALUE;
        }

        @Override
        protected Long getConfiguredMinimumValue() {
          return MIN_VALUE;
        }

        public void setGrouping(boolean grouping) {
          setGroupingUsed(grouping);
        }
      }

      @Order(60.0)
      public class Place1Field extends AbstractPlaceholderField {
      }

      @Order(70.0)
      public class Place2Field extends AbstractPlaceholderField {
      }

      @Order(80.0)
      public class Place3Field extends AbstractPlaceholderField {
      }

      @Order(90.0)
      public class BigIntegerInputField extends AbstractBigIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BigIntegerFieldInput");
        }

        @Override
        protected Long getConfiguredMaxValue() {
          return MAX_VALUE;
        }

        @Override
        protected Long getConfiguredMinValue() {
          return MIN_VALUE;
        }

        public void setGrouping(boolean grouping) {
          setGroupingUsed(grouping);
        }
      }

      @Order(100.0)
      public class Place4Field extends AbstractPlaceholderField {
      }

      @Order(110.0)
      public class Place5Field extends AbstractPlaceholderField {
      }

      @Order(120.0)
      public class Place6Field extends AbstractPlaceholderField {
      }
    }

    @Order(40.0)
    public class HighestValueButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("HighestValue");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getInputField().setValue(Integer.MAX_VALUE);
        getLongInputField().setValue(Long.MAX_VALUE);
        getBigIntegerInputField().setDisplayText("can get as large as you want");
      }
    }

    @Order(50.0)
    public class SmallestValueButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("SmallestValue");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getInputField().setValue(Integer.MIN_VALUE);
        getLongInputField().setValue(Long.MIN_VALUE);
        getBigIntegerInputField().setDisplayText("can get as small as you want");
      }
    }

    @Order(60.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
