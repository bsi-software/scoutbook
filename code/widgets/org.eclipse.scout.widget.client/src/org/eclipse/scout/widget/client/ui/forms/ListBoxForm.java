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

import java.awt.Color;
import java.util.ArrayList;

import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.listbox.AbstractListBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.data.basic.FontSpec;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;
import org.eclipse.scout.widget.client.services.lookup.FontStyleLookupCall;
import org.eclipse.scout.widget.client.services.lookup.UserContentListLookupCall;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.CloseButton;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.ConfigurationBox.CheckUncheckBox;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.ConfigurationBox.CheckUncheckBox.CheckAllButton;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.ConfigurationBox.CheckUncheckBox.UncheckAllButton;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.ConfigurationBox.FilterCheckedRowsValueField;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.ConfigurationBox.ListBoxField;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.ConfigurationBox.ListEntriesField;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.ExamplesBox;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.ExamplesBox.DefaultField;
import org.eclipse.scout.widget.client.ui.forms.ListBoxForm.MainBox.ExamplesBox.DisabledField;
import org.eclipse.scout.widget.shared.services.code.ColorsCodeType;

public class ListBoxForm extends AbstractForm implements IPageForm {

  public ListBoxForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ListBox");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  /**
   * @return the CheckAllButton
   */
  public CheckAllButton getCheckAllButton() {
    return getFieldByClass(CheckAllButton.class);
  }

  /**
   * @return the CheckUncheckBox
   */
  public CheckUncheckBox getCheckUncheckBox() {
    return getFieldByClass(CheckUncheckBox.class);
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

  public ConfigurationBox getConfigurationBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  /**
   * @return the FilterCheckedRowsValueField
   */
  public FilterCheckedRowsValueField getFilterCheckedRowsValueField() {
    return getFieldByClass(FilterCheckedRowsValueField.class);
  }

  /**
   * @return the ListBoxField
   */
  public ListBoxField getListBoxField() {
    return getFieldByClass(ListBoxField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the ListEntriesField
   */
  public ListEntriesField getListEntriesField() {
    return getFieldByClass(ListEntriesField.class);
  }

  /**
   * @return the UncheckAllButton
   */
  public UncheckAllButton getUncheckAllButton() {
    return getFieldByClass(UncheckAllButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class ExamplesBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Examples");
      }

      @Order(10.0)
      public class ListBoxWithCodeTypeContentField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("EmptyString");
        }

        @Override
        protected String getConfiguredFont() {
          return "BOLD";
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(TEXTS.get("ListBoxWithCodeTypeContent"));
        }
      }

      @Order(20.0)
      public class DefaultField extends AbstractListBox<Color> {

        @Override
        protected Class<? extends ICodeType> getConfiguredCodeType() {
          return ColorsCodeType.class;
        }

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }
      }

      @Order(30.0)
      public class DisabledField extends AbstractListBox<Color> {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected int getConfiguredGridH() {
          return 3;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Disabled");
        }

        @Override
        protected Class<? extends ICodeType> getConfiguredCodeType() {
          return ColorsCodeType.class;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(new Color[]{ColorsCodeType.RedCode.ID, ColorsCodeType.GreenCode.ID, ColorsCodeType.BlueCode.ID});
          setFilterCheckedRowsValue(true);
        }
      }

      @Order(40.0)
      public class ListBoxWithLookupCallContentField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("EmptyString");
        }

        @Override
        protected String getConfiguredFont() {
          return "BOLD";
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(TEXTS.get("ListBoxWithLookupCallContent"));
        }
      }

      @Order(50.0)
      public class DefaultListBox extends AbstractListBox<Integer> {

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }

        @Override
        protected Class<? extends LookupCall> getConfiguredLookupCall() {
          return FontStyleLookupCall.class;
        }
      }

      @Order(60.0)
      public class DisabledListBox extends AbstractListBox<Integer> {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected int getConfiguredGridH() {
          return 3;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Disabled");
        }

        @Override
        protected Class<? extends LookupCall> getConfiguredLookupCall() {
          return FontStyleLookupCall.class;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(new Integer[]{2, 3});
          setFilterCheckedRowsValue(true);
        }
      }
    }

    @Order(20.0)
    public class ConfigurationBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Configure");
      }

      private LookupRow createLookupRow(String key, String parent, String text, String icon, String tooltip, String font, String enabled, String active) {
        LookupRow row = new LookupRow(key, text, icon);

        // parent
        if (!StringUtility.isNullOrEmpty(parent)) {
          row.setParentKey(parent);
        }
        // tool tip
        if (!StringUtility.isNullOrEmpty(tooltip)) {
          row.setTooltipText(tooltip);
        }
        // font
        if (!StringUtility.isNullOrEmpty(font)) {
          FontSpec f = new FontSpec("Arial", 0, 12);

          if (UserAgentUtility.isSwtUi()) {
            f = new FontSpec("Arial", 0, 8);
          }

          if (font.equals("italic")) {
            row.setFont(f.getItalicCopy());
          }
          else if (font.equals("bold")) {
            row.setFont(f.getBoldCopy());
          }
        }
        // enabled
        if (enabled.equals("false")) {
          row.setEnabled(false);
        }
        // active
        if (!StringUtility.isNullOrEmpty(active) && active.equals("false")) {
          row.setActive(false);
        }

        return row;
      }

      @Order(10.0)
      public class ListBoxField extends AbstractListBox<String> {

        @Override
        protected int getConfiguredGridH() {
          return 4;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ListBox");
        }

        @Override
        protected Class<? extends LookupCall> getConfiguredLookupCall() {
          return UserContentListLookupCall.class;
        }
      }

      @Order(20.0)
      public class CheckUncheckBox extends AbstractSequenceBox {

        @Order(10.0)
        public class CheckAllButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("CheckAll");
          }

          @Override
          protected void execClickAction() throws ProcessingException {
            getListBoxField().checkAllKeys();
          }
        }

        @Order(20.0)
        public class UncheckAllButton extends AbstractLinkButton {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("UncheckAll");
          }

          @Override
          protected void execClickAction() throws ProcessingException {
            getListBoxField().uncheckAllKeys();
          }
        }
      }

      @Order(30.0)
      public class GetValueField extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("GetValue");
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return ListBoxForm.MainBox.ConfigurationBox.ListBoxField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          setValue(StringUtility.join(";", (String[]) newMasterValue));
        }
      }

      @Order(40.0)
      public class ListEntriesField extends AbstractStringField {

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ListContent");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected boolean getConfiguredMultilineText() {
          return true;
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          updateLookupRowEntries();
        }

        private void updateLookupRowEntries() {
          clearErrorStatus();

          ArrayList<LookupRow> rows = new ArrayList<LookupRow>();

          for (String line : getValue().split("\n")) {
            line = line.trim();

            if (line.length() > 0 && !line.startsWith("#")) {
              String[] t = line.split(";");

              if (t.length >= 6) {
                String active = "true";

                if (t.length == 7) {
                  active = t[6];
                }

                rows.add(createLookupRow(t[0], null, t[1], t[2], t[3], t[4], t[5], active));
              }
              else {
                setErrorStatus(TEXTS.get("LookupRowParseException" + ": '" + line + "'"));
              }
            }
          }

          ((UserContentListLookupCall) getListBoxField().getLookupCall()).setLookupRows(rows);
          try {
            getListBoxField().initField();
          }
          catch (ProcessingException e) {
            e.printStackTrace();
          }
        }
      }

      @Order(50.0)
      public class FilterCheckedRowsValueField extends AbstractCheckBox {

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("FilterCheckedRowsValue");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getListBoxField().setFilterCheckedRowsValue(getValue());
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
        ListEntriesField listEntries = getListEntriesField();
        listEntries.setValue(
            "# key;text;iconId;toolTip;font;enabled"
                + "\nRole100;Trainee;eye;Very limited experience;;true"
                + "\nRole110;Employee;eye;;bold;true"
                + "\nRole120;Secretary;eye;No longer available;italic;false"
                + "\nRole130;Manager;eye;Senior;;true");
        listEntries.updateLookupRowEntries();
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }

  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
