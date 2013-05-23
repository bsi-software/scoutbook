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
package org.eclipse.scout.widgets.client.ui.forms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDoubleColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widgets.client.ui.forms.TableFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widgets.client.ui.forms.TableFieldForm.MainBox.GroupBox;
import org.eclipse.scout.widgets.client.ui.forms.TableFieldForm.MainBox.GroupBox.EditableTableField;
import org.eclipse.scout.widgets.client.ui.forms.TableFieldForm.MainBox.GroupBox.NormalTableField;
import org.eclipse.scout.widgets.shared.Icons;

public class TableFieldForm extends AbstractForm implements IPageForm {

  public TableFieldForm() throws ProcessingException {
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
  protected String getConfiguredTitle() {
    return TEXTS.get("TableField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public EditableTableField getEditableTableField() {
    return getFieldByClass(EditableTableField.class);
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public NormalTableField getNormalTableField() {
    return getFieldByClass(NormalTableField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class NormalTableField extends AbstractTableField<NormalTableField.Table> {

        @Override
        protected int getConfiguredGridH() {
          return 4;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredTableStatusVisible() {
          return true;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          DateFormat df = new SimpleDateFormat("yyyy");
          Object data[][] = null;

          try {
            data = new Object[][]{
                {1L, "Mimas", 396, 0.9, df.parse("1789"), "William Herschel"},
                {2L, "Enceladus", 504, 1.4, df.parse("1789"), "William Herschel"},
                {3L, "Tethys", 1062, 1.9, df.parse("1684"), "Giovanni Domenico Cassini"},
                {4L, "Dione", 1123, 2.7, df.parse("1680"), "Giovanni Domenico Cassini"},
                {5L, "Rhea", 1527, 4.5, df.parse("1671"), "Giovanni Domenico Cassini"},
                {6L, "Titan", 5150, 16.0, df.parse("1655"), "Christiaan Huygens"},
                {7L, "Iapetus", 1470, 79.0, df.parse("1672"), "Giovanni Domenico Cassini"}
            };
          }
          catch (Exception e) {
          }

          getTable().addRowsByMatrix(data);
        }

        @Order(10.0)
        public class Table extends AbstractExtensibleTable {

          public NameColumn getNameColumn() {
            return getColumnSet().getColumnByClass(NameColumn.class);
          }

          public OrbitalPeriodDaysColumn getOrbitalPeriodDaysColumn() {
            return getColumnSet().getColumnByClass(OrbitalPeriodDaysColumn.class);
          }

          public DiscoveredColumn getDiscoveredColumn() {
            return getColumnSet().getColumnByClass(DiscoveredColumn.class);
          }

          @Override
          protected String getConfiguredDefaultIconId() {
            return Icons.Moon;
          }

          public AstronomerColumn getAstronomerColumn() {
            return getColumnSet().getColumnByClass(AstronomerColumn.class);
          }

          public DiameterKmColumn getDiameterKmColumn() {
            return getColumnSet().getColumnByClass(DiameterKmColumn.class);
          }

          public IDColumn getIDColumn() {
            return getColumnSet().getColumnByClass(IDColumn.class);
          }

          @Order(10.0)
          public class IDColumn extends AbstractLongColumn {

            @Override
            protected boolean getConfiguredDisplayable() {
              return false;
            }

            @Override
            protected boolean getConfiguredPrimaryKey() {
              return true;
            }

            @Override
            protected boolean getConfiguredVisible() {
              return false;
            }
          }

          @Order(20.0)
          public class NameColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Name");
            }

            @Override
            protected int getConfiguredWidth() {
              return 95;
            }
          }

          @Order(30.0)
          public class DiameterKmColumn extends AbstractIntegerColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("DiameterKm");
            }

            @Override
            protected int getConfiguredWidth() {
              return 86;
            }
          }

          @Order(40.0)
          public class OrbitalPeriodDaysColumn extends AbstractDoubleColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("OrbitalPeriodDays");
            }

            @Override
            protected int getConfiguredWidth() {
              return 126;
            }
          }

          @Order(50.0)
          public class DiscoveredColumn extends AbstractDateColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Discovered");
            }

            @Override
            protected int getConfiguredWidth() {
              return 84;
            }
          }

          @Order(60.0)
          public class AstronomerColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Astronomer");
            }

            @Override
            protected int getConfiguredWidth() {
              return 163;
            }
          }
        }
      }

      @Order(30.0)
      public class EditableTableField extends AbstractTableField<EditableTableField.Table> {

        @Override
        protected int getConfiguredGridH() {
          return 4;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredTableStatusVisible() {
          return true;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          Object data[][] = new Object[][]{
              {1L, "Exxon Mobil Corporation", "XOM"},
              {2L, "IBM", "IBM"},
              {3L, "UBS", "UBS"},
              {4L, "Coca-Cola Company", "KO"}};
          getTable().addRowsByMatrix(data);
        }

        @Order(10.0)
        public class Table extends AbstractTable {

          @Override
          protected boolean getConfiguredAutoResizeColumns() {
            return true;
          }

          @Override
          protected boolean getConfiguredMultiSelect() {
            return false;
          }

          public CompanyNrColumn getCompanyNrColumn() {
            return getColumnSet().getColumnByClass(CompanyNrColumn.class);
          }

          public NameColumn getNameColumn() {
            return getColumnSet().getColumnByClass(NameColumn.class);
          }

          public SymbolColumn getSymbolColumn() {
            return getColumnSet().getColumnByClass(SymbolColumn.class);
          }

          @Order(10.0)
          public class CompanyNrColumn extends AbstractLongColumn {

            @Override
            protected boolean getConfiguredDisplayable() {
              return false;
            }
          }

          @Order(20.0)
          public class NameColumn extends AbstractStringColumn {

            @Override
            protected boolean getConfiguredEditable() {
              return true;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Name");
            }

            @Override
            protected boolean execIsEditable(ITableRow row) throws ProcessingException {
              if (getCompanyNrColumn().getValue(row) >= 5L) {
                return super.execIsEditable(row);
              }
              return false;
            }
          }

          @Order(30.0)
          public class SymbolColumn extends AbstractStringColumn {

            @Override
            protected boolean getConfiguredEditable() {
              return true;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Symbol");
            }

            @Override
            protected boolean execIsEditable(ITableRow row) throws ProcessingException {
              if (getCompanyNrColumn().getValue(row) >= 5L) {
                return super.execIsEditable(row);
              }
              return false;
            }
          }

          @Order(10.0)
          public class NewCompanyMenu extends AbstractMenu {

            @Override
            protected boolean getConfiguredEmptySpaceAction() {
              return true;
            }

            @Override
            protected boolean getConfiguredSingleSelectionAction() {
              return false;
            }

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("NewCompany");
            }

            @Override
            protected void execAction() throws ProcessingException {
              getEditableTableField().getTable().addRowByArray(new Object[]{getEditableTableField().getTable().getCompanyNrColumn().getValues().length + 1, "New Company", ""});
            }
          }
        }
      }
    }

    @Order(20.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
