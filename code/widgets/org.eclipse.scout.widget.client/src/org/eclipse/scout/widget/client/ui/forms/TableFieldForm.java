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

import java.io.BufferedInputStream;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.NumberUtility;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.dnd.FileListTransferObject;
import org.eclipse.scout.commons.dnd.TransferObject;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.ProcessingStatus;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.filechooser.FileChooser;
import org.eclipse.scout.rt.client.ui.basic.table.ColumnSet;
import org.eclipse.scout.rt.client.ui.basic.table.ITable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.TableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateTimeColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractObjectColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.IColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.placeholder.AbstractPlaceholderField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.common.shell.IShellService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;
import org.eclipse.scout.service.SERVICES;
import org.eclipse.scout.widget.client.Activator;
import org.eclipse.scout.widget.client.services.lookup.IconIdLookupCall;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.AutoResizeColumnsField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.DefaultIconIdField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.DeletedRowsField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.HeaderRowVisibleField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.InsertedRowsField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.IsEditableField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.MultiSelectField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.PlaceholderField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.SelectedRowsField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.TableField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.TableField.Table.LocationColumn;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.TableStatusVisibleField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.UpdatedRowsField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ExamplesBox;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ExamplesBox.DefaultField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ExamplesBox.DefaultField.Table.DeleteMenu;
import org.eclipse.scout.widget.shared.FileCodeType;
import org.eclipse.scout.widget.shared.Icons;
import org.eclipse.scout.widget.shared.services.code.IndustryICBCodeType;

public class TableFieldForm extends AbstractForm implements IPageForm {

  public TableFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("TableField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  /**
   * @return the AutoResizeColumnsField
   */
  public AutoResizeColumnsField getAutoResizeColumnsField() {
    return getFieldByClass(AutoResizeColumnsField.class);
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
   * @return the HeaderRowVisibleField
   */
  public HeaderRowVisibleField getHeaderRowVisibleField() {
    return getFieldByClass(HeaderRowVisibleField.class);
  }

  /**
   * @return the InsertedRowsField
   */
  public InsertedRowsField getInsertedRowsField() {
    return getFieldByClass(InsertedRowsField.class);
  }

  /**
   * @return the IsEditableField
   */
  public IsEditableField getIsEditableField() {
    return getFieldByClass(IsEditableField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the MultiSelectField
   */
  public MultiSelectField getMultiSelectField() {
    return getFieldByClass(MultiSelectField.class);
  }

  /**
   * @return the PlaceholderField
   */
  public PlaceholderField getPlaceholderField() {
    return getFieldByClass(PlaceholderField.class);
  }

  /**
   * @return the SelectedRowsField
   */
  public SelectedRowsField getSelectedRowsField() {
    return getFieldByClass(SelectedRowsField.class);
  }

  /**
   * @return the TableField
   */
  public TableField getTableField() {
    return getFieldByClass(TableField.class);
  }

  /**
   * @return the TableStatusVisibleField
   */
  public TableStatusVisibleField getTableStatusVisibleField() {
    return getFieldByClass(TableStatusVisibleField.class);
  }

  public UpdatedRowsField getUpdatedRowsField() {
    return getFieldByClass(UpdatedRowsField.class);
  }

  /**
   * @return the DefaultIconIdField
   */
  public DefaultIconIdField getDefaultIconIdField() {
    return getFieldByClass(DefaultIconIdField.class);
  }

  public DeletedRowsField getDeletedRowsField() {
    return getFieldByClass(DeletedRowsField.class);
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
      public class DefaultField extends AbstractTableField {

        protected static final String FILE_SIZE_FORMAT = "#,### KB";
        protected static final long FILE_SIZE_FACTOR = 1024;

        @Override
        protected int getConfiguredGridH() {
          return 4;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }

        @Override
        protected String getConfiguredTooltipText() {
          return TEXTS.get("TableContextMenus");
        }

        @Override
        protected void execInitField() throws ProcessingException {
          URL url = Activator.getDefault().getBundle().getResource("/resources/images/bird_1008.jpg");

          try {
            byte[] content = IOUtility.getContent(new BufferedInputStream(url.openStream()));
            File file = IOUtility.createTempFile("bird.jpg", (byte[]) content);
            getTable().addRowByArray(fileToArray(file));
          }
          catch (Exception e) {
            throw new ProcessingException("", e);
          }
        }

        private Object[] fileToArray(File file) {
          String type = IOUtility.getFileExtension(file.getName());
          Long size = file.length();

          if (file.isDirectory()) {
            type = FileCodeType.DirectoryCode.ID;
            size = null;
          }
          else {
            if (CODES.getCodeType(FileCodeType.class).getCode(type) == null) {
              type = FileCodeType.UknownCode.ID;
            }

            size /= FILE_SIZE_FACTOR;
          }

          return new Object[]{file, file.getName(), size, type, new Date(file.lastModified()), !file.canWrite()};
        }

        @Order(10.0)
        public class DeleteKeyStroke extends AbstractKeyStroke {

          @Override
          protected String getConfiguredKeyStroke() {
            return "delete";
          }

          @Override
          protected boolean getConfiguredMultiSelectionAction() {
            return true;
          }

          @Override
          protected void execAction() throws ProcessingException {
            getTable().getMenu(DeleteMenu.class).execAction();
          }
        }

        @Order(10.0)
        public class Table extends AbstractExtensibleTable {

          private Set<File> m_keys = new HashSet<File>();

          public SizeColumn getSizeColumn() {
            return getColumnSet().getColumnByClass(SizeColumn.class);
          }

          public TypeColumn getTypeColumn() {
            return getColumnSet().getColumnByClass(TypeColumn.class);
          }

          public ReadOnlyColumn getReadOnlyColumn() {
            return getColumnSet().getColumnByClass(ReadOnlyColumn.class);
          }

          @Override
          protected boolean getConfiguredAutoResizeColumns() {
            return true;
          }

          @Override
          protected String getConfiguredDefaultIconId() {
            return AbstractIcons.ComposerFieldEntity;
          }

          @Override
          protected int getConfiguredDragType() {
            return TYPE_FILE_TRANSFER;
          }

          @Override
          protected int getConfiguredDropType() {
            return TYPE_FILE_TRANSFER;
          }

          @Override
          protected TransferObject execCopy(List<? extends ITableRow> rows) throws ProcessingException {
            //TODO [mzi] Auto-generated method stub.
            // method call leads to runtime exceptions: tunnel is null ??
            return super.execCopy(rows);
          }

          @Override
          protected TransferObject execDrag(List<ITableRow> rows) throws ProcessingException {
            List<File> files = new ArrayList<File>();

            for (ITableRow row : rows) {
              files.add((File) row.getKeyValues().get(0));
            }

            return new FileListTransferObject(files);
          }

          @Override
          protected void execDrop(ITableRow row, TransferObject t) throws ProcessingException {
            clearErrorStatus();

            try {
              if (t.isFileList()) {
                for (File file : ((FileListTransferObject) t).getFiles()) {
                  addFile(file);
                }
              }
            }
            catch (Exception e) {
              setErrorStatus(e.getMessage());
              throw e;
            }
          }

          protected void addFile(File file) throws ProcessingException {
            if (m_keys.contains(file)) {
              return;
            }

            ITableRow addedRow = addRowByArray(fileToArray(file));

            if (file.isDirectory()) {
              addedRow.setIconId(AbstractIcons.TreeNode);
            }

            m_keys.add(file);
          }

          @Override
          protected void execRowAction(ITableRow row) throws ProcessingException {
            getMenu(OpenMenu.class).execAction();
          }

          public DateModifiedColumn getDateModifiedColumn() {
            return getColumnSet().getColumnByClass(DateModifiedColumn.class);
          }

          public FileColumn getFileColumn() {
            return getColumnSet().getColumnByClass(TableFieldForm.MainBox.ExamplesBox.DefaultField.Table.FileColumn.class);
          }

          public NameColumn getNameColumn() {
            return getColumnSet().getColumnByClass(NameColumn.class);
          }

          @Order(10.0)
          public class FileColumn extends AbstractObjectColumn {

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
            protected int getConfiguredSortIndex() {
              return 0;
            }
          }

          @Order(30.0)
          public class SizeColumn extends AbstractLongColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Size");
            }

            @Override
            protected void execInitColumn() throws ProcessingException {
              setFormat(new DecimalFormat(FILE_SIZE_FORMAT));
            }
          }

          @Order(40.0)
          public class TypeColumn extends AbstractSmartColumn<String> {

            @Override
            protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
              return FileCodeType.class;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Type");
            }
          }

          @Order(50.0)
          public class DateModifiedColumn extends AbstractDateTimeColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("DateModified");
            }
          }

          @Order(60.0)
          public class ReadOnlyColumn extends AbstractBooleanColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("ReadOnly");
            }
          }

          @Order(10.0)
          public class OpenMenu extends AbstractExtensibleMenu {

            @Override
            protected boolean getConfiguredMultiSelectionAction() {
              return true;
            }

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("OpenFile");
            }

            @Override
            protected void execAction() throws ProcessingException {
              for (ITableRow row : getSelectedRows()) {
                File file = (File) row.getKeyValues().get(0);
                SERVICES.getService(IShellService.class).shellOpen(file.getPath());
              }
            }
          }

          @Order(20.0)
          public class AddMenu extends AbstractExtensibleMenu {

            @Override
            protected boolean getConfiguredEmptySpaceAction() {
              return true;
            }

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("AddFile");
            }

            @Override
            protected void execAction() throws ProcessingException {
              // TODO: [BUG] file chooser seems to be pretty broken ...
              // issue (1) set type load = true, dialog still says "save as..." and asks if it should overwrite file ...
              // issue (2) multi select doesn't work
              FileChooser fc = new FileChooser();
              List<File> files = fc.startChooser();
              fc.setTypeLoad(true);
              fc.setMultiSelect(true);

              for (File file : files) {
                addFile(file);
              }
            }

          }

          @Order(30.0)
          public class DeleteMenu extends AbstractExtensibleMenu {

            @Override
            protected boolean getConfiguredMultiSelectionAction() {
              return true;
            }

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("DeleteFile");
            }

            @Override
            protected void execAction() throws ProcessingException {
              for (ITableRow row : getSelectedRows()) {
                m_keys.remove(row.getKeyValues().get(0));
                row.delete();
              }
            }
          }
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
      public class TableField extends AbstractTableField {

        private static final String EDITABLE_CELL_BACKGROUND_COLOR = "EFEFFF";

        private long maxId = 0;

        @Override
        protected int getConfiguredGridH() {
          return 4;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TableField");
        }

        @Override
        protected void execInitField() throws ProcessingException {
          try {
            getTable().addRowByArray(new Object[]{++maxId, "Eclipsecon USA", "San Francisco, USA", DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY).parse("18.03.14"), IndustryICBCodeType.ICB9000.ICB9500.ICB9530.ICB9537.ID, 680L, "http://www.eclipsecon.org"});
            getTable().addRowByArray(new Object[]{++maxId, "Javaland", "Brühl, Germany", DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY).parse("25.03.14"), IndustryICBCodeType.ICB9000.ICB9500.ICB9530.ICB9537.ID, 810L, "http://www.javaland.eu"});
          }
          catch (ParseException e) {
            throw new ProcessingException(e.getMessage());
          }
        }

        @Override
        protected void execUpdateTableStatus() {
          super.execUpdateTableStatus();

          ITable table = getTable();

          getSelectedRowsField().setValue(rowsToKeyString(table.getSelectedRows()));
          // TODO [BUG] getinsertedrows doesn't return anything regardless of inserting rows
          getInsertedRowsField().setValue(rowsToKeyString(table.getInsertedRows()));
          // TODO [BUG] getupdatedrows also returns inserting rows. bug?
          getUpdatedRowsField().setValue(rowsToKeyString(table.getUpdatedRows()));
          getDeletedRowsField().setValue(rowsToKeyString(table.getDeletedRows()));
        }

        private String rowsToKeyString(List<ITableRow> list) {
          if (list == null || list.size() == 0) {
            return "";
          }

          StringBuffer buf = new StringBuffer(Long.toString((Long) list.get(0).getCellValue(0)));
          for (int i = 1; i < list.size(); i++) {
            buf.append(";" + (Long) list.get(i).getCellValue(0));
          }

          return buf.toString();
        }

        @Order(10.0)
        public class Table extends AbstractExtensibleTable {

          // TODO: [BUG] Table bug: organize columns throws null pointer exception

          public NameColumn getNameColumn() {
            return getColumnSet().getColumnByClass(NameColumn.class);
          }

          public LocationColumn getLocationColumn() {
            return getColumnSet().getColumnByClass(LocationColumn.class);
          }

          public IndustryColumn getIndustryColumn() {
            return getColumnSet().getColumnByClass(IndustryColumn.class);
          }

          public ParticipantsColumn getParticipantsColumn() {
            return getColumnSet().getColumnByClass(ParticipantsColumn.class);
          }

          public WebPageColumn getWebPageColumn() {
            return getColumnSet().getColumnByClass(WebPageColumn.class);
          }

          public DateColumn getDateColumn() {
            return getColumnSet().getColumnByClass(DateColumn.class);
          }

          public IdColumn getIdColumn() {
            return getColumnSet().getColumnByClass(IdColumn.class);
          }

          /**
           * if editable field is set to true and row-id > 2 true is returned.
           * otherwise, false is returned.
           */
          private boolean isEditable(ITableRow row) {
            return getIsEditableField().getValue() && getIdColumn().getValue(row) > 2;
          }

          @Order(10.0)
          public class IdColumn extends AbstractLongColumn {

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
            protected boolean getConfiguredEditable() {
              return true;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Name");
            }

            @Override
            protected boolean execIsEditable(ITableRow row) throws ProcessingException {
              if (Table.this.isEditable(row)) {
                row.getCellForUpdate(getNameColumn()).setBackgroundColor(EDITABLE_CELL_BACKGROUND_COLOR);
                return true;
              }
              return false;
            }

            @Override
            protected String execValidateValue(ITableRow row, String rawValue) throws ProcessingException {
              Cell cell = row.getCellForUpdate(getNameColumn());

              if (StringUtility.isNullOrEmpty(rawValue)) {
                cell.setErrorStatus(new ProcessingStatus(TEXTS.get("NoEmptyName"), IStatus.ERROR));
                cell.setIconId(Icons.StatusError);
              }
              else {
                cell.clearErrorStatus();
                cell.setIconId(null);
              }

              return rawValue;
            }
          }

          @Order(30.0)
          public class LocationColumn extends AbstractStringColumn {

            @Override
            protected boolean getConfiguredEditable() {
              return true;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Location");
            }

            @Override
            protected boolean execIsEditable(ITableRow row) throws ProcessingException {
              if (Table.this.isEditable(row)) {
                row.getCellForUpdate(getLocationColumn()).setBackgroundColor(EDITABLE_CELL_BACKGROUND_COLOR);
                return true;
              }
              return false;
            }
          }

          @Order(40.0)
          public class DateColumn extends AbstractDateColumn {

            @Override
            protected boolean getConfiguredEditable() {
              return true;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Date");
            }

            @Override
            protected boolean execIsEditable(ITableRow row) throws ProcessingException {
              if (Table.this.isEditable(row)) {
                row.getCellForUpdate(getDateColumn()).setBackgroundColor(EDITABLE_CELL_BACKGROUND_COLOR);
                return true;
              }
              return false;
            }
          }

          @Order(50.0)
          public class IndustryColumn extends AbstractSmartColumn<Long> {

            @Override
            protected boolean getConfiguredEditable() {
              return true;
            }

            @Override
            protected boolean getConfiguredBrowseHierarchy() {
              return true;
            }

            @Override
            protected Class<? extends ICodeType<?, Long>> getConfiguredCodeType() {
              return IndustryICBCodeType.class;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Industry");
            }

            @Override
            protected boolean execIsEditable(ITableRow row) throws ProcessingException {
              if (Table.this.isEditable(row)) {
                row.getCellForUpdate(getIndustryColumn()).setBackgroundColor(EDITABLE_CELL_BACKGROUND_COLOR);
                return true;
              }
              return false;
            }
          }

          @Order(70.0)
          public class ParticipantsColumn extends AbstractLongColumn {

            @Override
            protected boolean getConfiguredEditable() {
              return true;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Participans");
            }

            @Override
            protected boolean execIsEditable(ITableRow row) throws ProcessingException {
              if (getIsEditableField().getValue()) {
                row.getCellForUpdate(getParticipantsColumn()).setBackgroundColor(EDITABLE_CELL_BACKGROUND_COLOR);
                return true;
              }
              return false;
            }

            @Override
            protected Long execValidateValue(ITableRow row, Long rawValue) throws ProcessingException {
              Cell cell = row.getCellForUpdate(this);

              if (NumberUtility.nvl(rawValue, 1) >= 0) {
                cell.clearErrorStatus();
                cell.setIconId(null);
              }
              else {
                cell.setErrorStatus(new ProcessingStatus(TEXTS.get("NoNegNumber"), IStatus.ERROR));
                cell.setIconId(Icons.StatusError);
              }

              return rawValue;
            }
          }

          @Order(80.0)
          public class WebPageColumn extends AbstractStringColumn {

            @Override
            protected boolean getConfiguredEditable() {
              return true;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("WebPage");
            }

            @Override
            protected boolean getConfiguredVisible() {
              return false;
            }

            @Override
            protected boolean execIsEditable(ITableRow row) throws ProcessingException {
              if (Table.this.isEditable(row)) {
                row.getCellForUpdate(getIndustryColumn()).setBackgroundColor(EDITABLE_CELL_BACKGROUND_COLOR);
                return true;
              }
              return false;
            }
          }

          @Order(10.0)
          public class NewEventMenu extends AbstractExtensibleMenu {

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
              return TEXTS.get("NewEvent");
            }

            @Override
            protected void execAction() throws ProcessingException {
              ColumnSet cols = getColumnSet();
              ITableRow row = new TableRow(cols);

              row.getCellForUpdate(getIdColumn()).setValue(++maxId);
              row.getCellForUpdate(getNameColumn()).setValue("New Event");

              addRow(row);
            }
          }

          @Order(20.0)
          public class DeleteEventMenu extends AbstractExtensibleMenu {

            @Override
            protected boolean getConfiguredMultiSelectionAction() {
              return true;
            }

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("DeleteEvent");
            }

            @Override
            protected void execAction() throws ProcessingException {
              List<ITableRow> rows = getSelectedRows();
              deleteRows(rows);
            }
          }
        }
      }

      @Order(20.0)
      public class SelectedRowsField extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("SelectedRows");
        }
      }

      @Order(30.0)
      public class InsertedRowsField extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("InsertedRows");
        }
      }

      @Order(40.0)
      public class UpdatedRowsField extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("UpdatedRows");
        }
      }

      @Order(50.0)
      public class DeletedRowsField extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DeletedRows");
        }
      }

      @Order(60.0)
      public class DefaultIconIdField extends AbstractSmartField<String> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DefaultIconId");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return (Class<? extends ILookupCall<String>>) IconIdLookupCall.class;
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getTableField().getTable().setDefaultIconId(getValue());

          // setting the default icon id for existing rows doesn't work.
          // that's why we are setting the icon on all table rows individually as well
          for (ITableRow row : getTableField().getTable().getRows()) {
            row.setIconId(getValue());
          }
        }
      }

      @Order(70.0)
      public class PlaceholderField extends AbstractPlaceholderField {
      }

      @Order(80.0)
      public class AutoResizeColumnsField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("AutoResizeColumns");
        }

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getTableField().getTable().setAutoResizeColumns(getValue());
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getTableField().getTable().isAutoResizeColumns());
        }

      }

      @Order(90.0)
      public class IsVisibleField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("IsVisible");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getLocationColumn().setVisible(getValue());
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getLocationColumn().isVisible());
        }

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        private IColumn<String> getLocationColumn() {
          ITable table = getTableField().getTable();
          return table.getColumnSet().getColumnByClass(LocationColumn.class);
        }
      }

      @Order(100.0)
      public class IsEditableField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("IsEditable");
        }

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }
      }

      @Order(110.0)
      public class MultiSelectField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MultiSelect");
        }

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getTableField().getTable().setMultiSelect(getValue());
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getTableField().getTable().isMultiSelect());
          setValue(getTableField().getTable().isHeaderVisible());

          if (!UserAgentUtility.isSwingUi()) {
            setTooltipText(TEXTS.get("ThisPropertyCannotBeChangedAtRuntime"));
            setEnabled(false);
          }
        }
      }

      @Order(120.0)
      public class TableStatusVisibleField extends AbstractBooleanField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TableStatusVisible");
        }

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected String getConfiguredTooltipText() {
          return TEXTS.get("ThisPropertyCannotBeChangedAtRuntime");
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(true);
          getTableField().setTableStatusVisible(true);
        }
      }

      @Order(130.0)
      public class HeaderRowVisibleField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("HeaderRowVisible");
        }

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getTableField().getTable().setHeaderVisible(getValue());
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getTableField().getTable().isHeaderVisible());

          if (UserAgentUtility.isSwingUi()) {
            setTooltipText(TEXTS.get("SwingUIRestriction") + TEXTS.get("ThisPropertyCannotBeChangedAtRuntime"));
            setEnabled(false);
          }
        }
      }
    }

    @Order(40.0)
    public class SampleContentButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("SampleContent");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
      }
    }

    @Order(50.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
