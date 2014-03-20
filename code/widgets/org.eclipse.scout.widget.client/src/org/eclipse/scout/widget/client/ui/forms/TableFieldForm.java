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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.dnd.FileListTransferObject;
import org.eclipse.scout.commons.dnd.TransferObject;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateTimeColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractObjectColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.basic.tree.AbstractTreeNode;
import org.eclipse.scout.rt.client.ui.basic.tree.ITree;
import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNode;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.client.ui.form.fields.treefield.AbstractTreeField;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.extension.client.ui.basic.tree.AbstractExtensibleTree;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.common.shell.IShellService;
import org.eclipse.scout.service.SERVICES;
import org.eclipse.scout.widget.client.Activator;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.CloseButton;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.MenuContentField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.TreeEntriesField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ConfigurationBox.TreeField;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ExamplesBox;
import org.eclipse.scout.widget.client.ui.forms.TableFieldForm.MainBox.ExamplesBox.DefaultField;
import org.eclipse.scout.widget.client.ui.template.formfield.AbstractUserTreeField;
import org.eclipse.scout.widget.shared.FileCodeType;

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

  public TreeField getTreeField() {
    return getFieldByClass(TreeField.class);
  }

  public ConfigurationBox getConfigurationBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the MenuContentField
   */
  public MenuContentField getMenuContentField() {
    return getFieldByClass(MenuContentField.class);
  }

  /**
   * @return the TreeEntriesField
   */
  public TreeEntriesField getTreeEntriesField() {
    return getFieldByClass(TreeEntriesField.class);
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

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
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
          return new Object[]{file, file.getName(), file.length(), IOUtility.getFileExtension(file.getName()), new Date(file.lastModified()), !file.canWrite()};
        }

        @Order(10.0)
        public class Table extends AbstractExtensibleTable {

          /**
           * @return the SizeColumn
           */
          public SizeColumn getSizeColumn() {
            return getColumnSet().getColumnByClass(SizeColumn.class);
          }

          /**
           * @return the TypeColumn
           */
          public TypeColumn getTypeColumn() {
            return getColumnSet().getColumnByClass(TypeColumn.class);
          }

          /**
           * @return the ReadOnlyColumn
           */
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
          protected TransferObject execDrag(ITableRow[] rows) throws ProcessingException {
            List<File> files = new ArrayList<File>();

            for (ITableRow row : rows) {
              files.add((File) row.getKeyValues()[0]);
            }

            return new FileListTransferObject(files);
          }

          @Override
          protected void execDrop(ITableRow row, TransferObject t) throws ProcessingException {
            clearErrorStatus();

            if (t.isFileList()) {
              for (File file : ((FileListTransferObject) t).getFileList()) {
                ITableRow addedRow = addRowByArray(fileToArray(file));

                if (file.isDirectory()) {
                  addedRow.setIconId(AbstractIcons.TreeNode);
                }
              }
            }
          }

          @Override
          protected void execRowAction(ITableRow row) throws ProcessingException {
            getMenu(OpenMenu.class).execAction();
          }

          /**
           * @return the DateModifiedColumn
           */
          public DateModifiedColumn getDateModifiedColumn() {
            return getColumnSet().getColumnByClass(DateModifiedColumn.class);
          }

          /**
           * @return the FileColumn
           */
          public FileColumn getFileColumn() {
            return getColumnSet().getColumnByClass(TableFieldForm.MainBox.ExamplesBox.DefaultField.Table.FileColumn.class);
          }

          /**
           * @return the NameColumn
           */
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
          }

          @Order(40.0)
          public class TypeColumn extends AbstractSmartColumn<String> {

            @Override
            protected Class<? extends ICodeType> getConfiguredCodeType() {
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
              return TEXTS.get("Open");
            }

            @Override
            protected void execAction() throws ProcessingException {
              for (ITableRow row : getSelectedRows()) {
                File file = (File) row.getKeyValues()[0];
                SERVICES.getService(IShellService.class).shellOpen(file.getPath());
              }
            }
          }

          @Order(20.0)
          public class DeleteMenu extends AbstractExtensibleMenu {

            @Override
            protected boolean getConfiguredMultiSelectionAction() {
              return true;
            }

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("DeleteMenu");
            }

            @Override
            protected void execAction() throws ProcessingException {
              for (ITableRow row : getSelectedRows()) {
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
      public class TreeField extends AbstractTreeField {

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TreeField");
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setTree(new Tree(), false);
        }

        @Order(10.0)
        public class Tree extends AbstractExtensibleTree {
        }
      }

      @Order(20.0)
      public class TreeEntriesField extends AbstractUserTreeField {

        @Override
        protected int getConfiguredGridH() {
          return 3;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TreeContent");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          ITree tree = getTreeField().getTree();
          List<Node> nodes = parseFieldValue(true);

          tree.removeAllChildNodes(tree.getRootNode());
          addNodesToTree(nodes, tree, tree.getRootNode());
        }

      }

      @Order(30.0)
      public class MenuContentField extends AbstractUserTreeField {

        @Override
        protected int getConfiguredGridH() {
          return 2;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MenuContent");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          List<Node> nodes = parseFieldValue(true);
          getTreeField().getTree().setMenus(nodesToMenus(nodes).toArray(new IMenu[]{}));
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
        treeEntries.setValue(TEXTS.get("TreeUserContent"));
        getMenuContentField().setValue(TEXTS.get("MenuUserContent"));
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }

    /**
     * recursive function to convert codes (enumerations) into an abstract tree.
     */
    private void addCodesToTree(ICode[] codes, ITreeNode parent, AbstractExtensibleTree tree) {
      // create a tree node for each code
      for (final ICode code : codes) {
        AbstractTreeNode node = new AbstractTreeNode() {
          @Override
          protected void execDecorateCell(Cell cell) {
            cell.setIconId(code.getIconId());
            cell.setText(code.getText());
            cell.setTooltipText(code.getTooltipText());
          }
        };

        // add the tree node to the tree
        tree.addChildNode(parent, node);

        // recursively add child nodes (if any)
        ICode[] children = code.getChildCodes();
        if (children.length > 0) {
          addCodesToTree(children, node, tree);
        }
      }
    }

    /**
     * recursive function to mark tree nodes without children as leaf nodes
     */
    private void updateLeafNodes(ITreeNode node) {
      ITreeNode[] children = node.getChildNodes();
      node.setLeaf(children.length == 0);

      if (children.length > 0) {
        for (ITreeNode child : children) {
          updateLeafNodes(child);
        }
      }
    }

  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
