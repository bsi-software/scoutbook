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

import java.util.ArrayList;
import java.util.Calendar;

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
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.treebox.AbstractTreeBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.data.basic.FontSpec;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;
import org.eclipse.scout.widget.client.services.lookup.UserContentTreeLookupCall;
import org.eclipse.scout.widget.client.services.lookup.YearsMonthsLookupCall;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.CloseButton;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ConfigurationBox.AutoCheckChildNodesField;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ConfigurationBox.CheckUncheckBox;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ConfigurationBox.CheckUncheckBox.CheckAllButton;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ConfigurationBox.CheckUncheckBox.UncheckAllButton;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ConfigurationBox.FilterCheckedRowsValueField;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ConfigurationBox.TreeBoxField;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ConfigurationBox.TreeEntriesField;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ExamplesBox;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ExamplesBox.DefaultField;
import org.eclipse.scout.widget.client.ui.forms.TreeBoxForm.MainBox.ExamplesBox.DisabledField;
import org.eclipse.scout.widget.shared.services.code.IndustryICBCodeType;

public class TreeBoxForm extends AbstractForm implements IPageForm {

  public TreeBoxForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("TreeBox");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  /**
   * @return the AutoCheckChildNodesField
   */
  public AutoCheckChildNodesField getAutoCheckChildNodesField() {
    return getFieldByClass(AutoCheckChildNodesField.class);
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
   * @return the TreeBoxField
   */
  public TreeBoxField getTreeBoxField() {
    return getFieldByClass(TreeBoxField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the TreeEntriesField
   */
  public TreeEntriesField getTreeEntriesField() {
    return getFieldByClass(TreeEntriesField.class);
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
      public class TreeBoxWithCodeTypeContentField extends AbstractLabelField {

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
          setValue(TEXTS.get("TreeBoxWithCodeTypeContent"));
        }
      }

      @Order(20.0)
      public class DefaultField extends AbstractTreeBox<Long> {

        @Override
        protected Class<? extends ICodeType> getConfiguredCodeType() {
          return IndustryICBCodeType.class;
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
      public class DisabledField extends AbstractTreeBox<Long> {

        @Override
        protected Class<? extends ICodeType> getConfiguredCodeType() {
          return IndustryICBCodeType.class;
        }

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
        protected void execInitField() throws ProcessingException {
          setValue(new Long[]{IndustryICBCodeType.ICB8000.ID, IndustryICBCodeType.ICB8000.ICB8500.ID, IndustryICBCodeType.ICB9000.ICB9500.ICB9530.ICB9537.ID,});
          setFilterCheckedNodesValue(true);
        }
      }

      @Order(40.0)
      public class TreeBoxWithLookupCallContentField extends AbstractLabelField {

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
          setValue(TEXTS.get("TreeBoxWithLookupCallContent"));
        }
      }

      @Order(50.0)
      public class DefaultTreeBoxField extends AbstractTreeBox<String> {

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
          return YearsMonthsLookupCall.class;
        }
      }

      @Order(60.0)
      public class DisabledTreeBoxField extends AbstractTreeBox<String> {

        @Override
        protected boolean getConfiguredAutoExpandAll() {
          return true;
        }

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
          return YearsMonthsLookupCall.class;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          int year = Calendar.getInstance().get(Calendar.YEAR);
          int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
          int key = 100 * year + month;
          setValue(new String[]{String.valueOf(key), String.valueOf(key + 1), String.valueOf(key + 2)});
          setFilterCheckedNodesValue(true);
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
      public class TreeBoxField extends AbstractTreeBox<String> {

        @Override
        protected int getConfiguredGridH() {
          return 4;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TreeBox");
        }

        @Override
        protected Class<? extends LookupCall> getConfiguredLookupCall() {
          return UserContentTreeLookupCall.class;
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
            getTreeBoxField().checkAllKeys();
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
            getTreeBoxField().uncheckAllKeys();
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
          return TreeBoxForm.MainBox.ConfigurationBox.TreeBoxField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          setValue(StringUtility.join(";", (String[]) newMasterValue));
        }
      }

      @Order(40.0)
      public class TreeEntriesField extends AbstractStringField {

        @Override
        protected int getConfiguredGridH() {
          return 3;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TreeContent");
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

              if (t.length >= 7) {
                String active = "true";

                if (t.length == 8) {
                  active = t[6];
                }
                rows.add(createLookupRow(t[0], t[1], t[2], t[3], t[4], t[5], t[6], active));
              }
              else {
                setErrorStatus(TEXTS.get("LookupRowParseException" + ": '" + line + "'"));
              }
            }
          }

          ((UserContentTreeLookupCall) getTreeBoxField().getLookupCall()).setLookupRows(rows);
          try {
            getTreeBoxField().initField();
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
          getTreeBoxField().setFilterCheckedNodesValue(getValue());
        }
      }

      @Order(60.0)
      public class AutoCheckChildNodesField extends AbstractCheckBox {

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("AutoCheckChildNodes");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getTreeBoxField().setAutoCheckChildNodes(getValue());
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
        TreeEntriesField treeEntries = getTreeEntriesField();
        treeEntries.setValue(TEXTS.get("TreeBoxUserContent"));
        treeEntries.updateLookupRowEntries();
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }

  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
