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
import java.util.Locale;

import org.eclipse.scout.commons.NumberUtility;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.placeholder.AbstractPlaceholderField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.data.basic.FontSpec;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;
import org.eclipse.scout.widget.client.services.lookup.LocaleLookupCall;
import org.eclipse.scout.widget.client.services.lookup.UserContentListLookupCall;
import org.eclipse.scout.widget.client.services.lookup.UserContentTreeLookupCall;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.BrowseAutoExpandAllField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.BrowseHierarchyField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.BrowseMaxRowCountField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.GetValueField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.ListEntriesField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.ListSmartField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.TreeEntriesField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ConfigurationBox.TreeSmartField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.DefaultField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.DefaultSmartField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.DisabledField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.DisabledSmartFieldField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.MandatoryField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.MandatorySmartfieldField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.SmartFieldWithListContentField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.ExamplesBox.SmartFieldWithTreeContentField;
import org.eclipse.scout.widget.client.ui.forms.SmartFieldForm.MainBox.SampleContentButton;
import org.eclipse.scout.widget.shared.services.code.ColorsCodeType;
import org.eclipse.scout.widget.shared.services.code.IndustryICBCodeType;
import org.eclipse.scout.widget.shared.services.code.IndustryICBCodeType.ICB9000.ICB9500.ICB9530.ICB9537;

public class SmartFieldForm extends AbstractForm implements IPageForm {

  public SmartFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("SmartField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  /**
   * @return the BrowseAutoExpandAllField
   */
  public BrowseAutoExpandAllField getBrowseAutoExpandAllField() {
    return getFieldByClass(BrowseAutoExpandAllField.class);
  }

  /**
   * @return the BrowseHierarchyField
   */
  public BrowseHierarchyField getBrowseHierarchyField() {
    return getFieldByClass(BrowseHierarchyField.class);
  }

  /**
   * @return the BrowseMaxRowCountField
   */
  public BrowseMaxRowCountField getBrowseMaxRowCountField() {
    return getFieldByClass(BrowseMaxRowCountField.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  /**
   * @return the DisabledSmartFieldField
   */
  public DisabledSmartFieldField getDisabledSmartFieldField() {
    return getFieldByClass(DisabledSmartFieldField.class);
  }

  public ExamplesBox getExamplesBox() {
    return getFieldByClass(ExamplesBox.class);
  }

  /**
   * @return the DefaultField
   */
  public DefaultField getDefaultField() {
    return getFieldByClass(DefaultField.class);
  }

  public DisabledField getDisabledField() {
    return getFieldByClass(DisabledField.class);
  }

  /**
   * @return the MandatoryField
   */
  public MandatoryField getMandatoryField() {
    return getFieldByClass(MandatoryField.class);
  }

  /**
   * @return the MandatorySmartfieldField
   */
  public MandatorySmartfieldField getMandatorySmartfieldField() {
    return getFieldByClass(MandatorySmartfieldField.class);
  }

  public ConfigurationBox getConfigurationBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  /**
   * @return the DefaultSmartField
   */
  public DefaultSmartField getDefaultSmartField() {
    return getFieldByClass(DefaultSmartField.class);
  }

  /**
   * @return the GetValueField
   */
  public GetValueField getGetValueField() {
    return getFieldByClass(GetValueField.class);
  }

  /**
   * @return the ListSmartField
   */
  public ListSmartField getListSmartField() {
    return getFieldByClass(ListSmartField.class);
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
   * @return the SmartFieldWithListContentField
   */
  public SmartFieldWithListContentField getSmartFieldWithListContentField() {
    return getFieldByClass(SmartFieldWithListContentField.class);
  }

  /**
   * @return the SmartFieldWithTreeContentField
   */
  public SmartFieldWithTreeContentField getSmartFieldWithTreeContentField() {
    return getFieldByClass(SmartFieldWithTreeContentField.class);
  }

  /**
   * @return the ListEntriesField
   */
  public TreeEntriesField getTreeEntriesField() {
    return getFieldByClass(TreeEntriesField.class);
  }

  /**
   * @return the SampleContentButton
   */
  public SampleContentButton getSampleContentButton() {
    return getFieldByClass(SampleContentButton.class);
  }

  /**
   * @return the TreeSmartField
   */
  public TreeSmartField getTreeSmartField() {
    return getFieldByClass(TreeSmartField.class);
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
      public class SmartFieldWithListContentField extends AbstractLabelField {

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
          setValue(TEXTS.get("SmartFieldWithListContent"));
        }
      }

      @Order(20.0)
      public class DefaultField extends AbstractSmartField<Locale> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }

        @Override
        protected Class<? extends LookupCall> getConfiguredLookupCall() {
          return LocaleLookupCall.class;
        }
      }

      @Order(30.0)
      public class MandatoryField extends AbstractSmartField<Color> {

        @Override
        protected Class<? extends ICodeType<?>> getConfiguredCodeType() {
          return ColorsCodeType.class;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          String color = Integer.toHexString(getValue().getRGB()).substring(2);
          setBackgroundColor(color);
        }
      }

      @Order(40.0)
      public class DisabledField extends AbstractSmartField<Color> {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Disabled");
        }

        @Override
        protected Class<? extends ICodeType<?>> getConfiguredCodeType() {
          return ColorsCodeType.class;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(new Color(255, 255, 255));
        }
      }

      @Order(50.0)
      public class SmartFieldWithTreeContentField extends AbstractLabelField {

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
          setValue(TEXTS.get("SmartFieldWithTreeContent"));
        }
      }

      @Order(60.0)
      public class DefaultSmartField extends AbstractSmartField<Object> {

        @Override
        protected Class<? extends ICodeType<?>> getConfiguredCodeType() {
          return IndustryICBCodeType.class;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }
      }

      @Order(70.0)
      public class MandatorySmartfieldField extends AbstractSmartField<Long> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected Class<? extends ICodeType<?>> getConfiguredCodeType() {
          return IndustryICBCodeType.class;
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }

      @Order(80.0)
      public class DisabledSmartFieldField extends AbstractSmartField<Long> {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Disabled");
        }

        @Override
        protected Class<? extends ICodeType<?>> getConfiguredCodeType() {
          return IndustryICBCodeType.class;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(ICB9537.ID);
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
      public class ListSmartField extends AbstractSmartField<String> {

        @Override
        protected boolean getConfiguredActiveFilterEnabled() {
          return true;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ListSmartField");
        }

        @Override
        protected Class<? extends LookupCall> getConfiguredLookupCall() {
          return UserContentListLookupCall.class;
        }
      }

      @Order(20.0)
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
          return SmartFieldForm.MainBox.ConfigurationBox.ListSmartField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          setValue(newMasterValue.toString());
        }
      }

      @Order(30.0)
      public class ListEntriesField extends AbstractStringField {

        @Override
        protected int getConfiguredGridH() {
          return 3;
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
          ((UserContentListLookupCall) getListSmartField().getLookupCall()).setLookupRows(rows);
        }
      }

      @Order(40.0)
      public class BrowseMaxRowCountField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BrowseMaxRowCount");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected Integer getConfiguredMinValue() {
          return 0;
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getListSmartField().setBrowseMaxRowCount(NumberUtility.nvl(getValue(), 100));
          getTreeSmartField().setBrowseMaxRowCount(NumberUtility.nvl(getValue(), 100));
        }
      }

      @Order(50.0)
      public class Placeholder1Field extends AbstractPlaceholderField {
      }

      @Order(70.0)
      public class TreeSmartField extends AbstractSmartField<String> {

        @Override
        protected boolean getConfiguredBrowseAutoExpandAll() {
          return false;
        }

        @Override
        protected boolean getConfiguredBrowseHierarchy() {
          return true;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TreeSmartField");
        }

        @Override
        protected Class<? extends LookupCall> getConfiguredLookupCall() {
          return UserContentTreeLookupCall.class;
        }
      }

      @Order(80.0)
      public class GetValueTreeSmartFieldField extends AbstractStringField {

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
          return SmartFieldForm.MainBox.ConfigurationBox.TreeSmartField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          setValue(newMasterValue.toString());
        }
      }

      @Order(90.0)
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

          ((UserContentTreeLookupCall) getTreeSmartField().getLookupCall()).setLookupRows(rows);
        }
      }

      @Order(100.0)
      public class BrowseHierarchyField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BrowseHierarchy");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getTreeSmartField().setBrowseHierarchy(getValue());
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getTreeSmartField().isBrowseHierarchy());
        }
      }

      @Order(110.0)
      public class BrowseAutoExpandAllField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BrowseAutoExpandAll");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getTreeSmartField().setBrowseAutoExpandAll(getValue());
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getTreeSmartField().isBrowseAutoExpandAll());
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

        TreeEntriesField treeEntries = getTreeEntriesField();
        treeEntries.setValue(
            "# key;parent;text;iconId;toolTip;font;enabled"
                + "\nAF;;Africa;tree_node;;;false"
                + "\nEA;AF;Eastern Africa;composerfield_attribute;;;true"
                + "\nMF;AF;Middle Africa;composerfield_attribute;;;true"
                + "\nNF;AF;Northern Africa;composerfield_attribute;;;true"
                + "\nSF;AF;Southern Africa;composerfield_attribute;;;true"
                + "\nWF;AF;Western Africa;composerfield_attribute;;;true"
                + "\nAM;;Americas;tree_node;;;false"
                + "\nLA;AM;Latin America;tree_node;;;false"
                + "\nSA;LA;South America;composerfield_attribute;;;true"
                + "\nCR;LA;Caribbean;composerfield_attribute;;;true"
                + "\nCA;LA;Central America;composerfield_attribute;;;true"
                + "\nNA;AM;Northern America;composerfield_attribute;;;true"
                + "\nAN;;Antarctica;tree_node;No available office;italic;false"
                + "\nAS;;Asia;tree_node;;;false"
                + "\nCS;AS;Central Asia;composerfield_attribute;;;true"
                + "\nES;AS;Eastern Asia;composerfield_attribute;;;true"
                + "\nSS;AS;Southern Asia;composerfield_attribute;;;true"
                + "\nSES;AS;South-Eastern Asia;composerfield_attribute;;;true"
                + "\nCS;AS;Western Asia;composerfield_attribute;;;true"
                + "\nER;;Europe;tree_node;;;false"
                + "\nEU;ER;Eastern Europe;composerfield_attribute;;;true"
                + "\nNU;ER;Northern Europe;composerfield_attribute;;;true"
                + "\nSU;ER;Southern Europe;composerfield_attribute;;;true"
                + "\nWU;ER;Western Europe;composerfield_attribute;;;true"
                + "\nOC;;Oceania;tree_node;No available office;italic;false");
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
