package org.eclipsescout.contacts.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.contacts.client.ui.forms.CompanyForm;
import org.eclipsescout.contacts.shared.services.IStandardOutlineService;

public class CompanyTablePage extends AbstractPageWithTable<CompanyTablePage.Table> {

  @Override
  protected String getConfiguredIconId() {
    return AbstractIcons.ComposerFieldEntity;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Company");
  }

  @Override
  protected IPage execCreateChildPage(ITableRow row) throws ProcessingException {
    PersonTablePage childPage = new PersonTablePage();
    childPage.setCompanyId(getTable().getCompanyIdColumn().getValue(row));
    return childPage;
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    return SERVICES.getService(IStandardOutlineService.class).getCompanyTableData();
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    public NameColumn getNameColumn() {
      return getColumnSet().getColumnByClass(NameColumn.class);
    }

    public LocationColumn getLocationColumn() {
      return getColumnSet().getColumnByClass(LocationColumn.class);
    }

    @Override
    protected String getConfiguredDefaultIconId() {
      return AbstractIcons.ComposerFieldEntity;
    }

    public CompanyIdColumn getCompanyIdColumn() {
      return getColumnSet().getColumnByClass(CompanyIdColumn.class);
    }

    @Order(10.0)
    public class CompanyIdColumn extends AbstractStringColumn {

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }

      @Override
      protected boolean getConfiguredPrimaryKey() {
        return true;
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
        return 221;
      }
    }

    @Order(30.0)
    public class LocationColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Location");
      }

      @Override
      protected int getConfiguredWidth() {
        return 144;
      }
    }

    @Order(10.0)
    public class EditCompanyMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("EditCompany");
      }

      @Override
      protected void execAction() throws ProcessingException {
        CompanyForm form = new CompanyForm();
        form.setCompanyId(getCompanyIdColumn().getSelectedValue());
        form.startModify();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(20.0)
    public class NewCompanyMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("NewCompany");
      }

      @Override
      protected boolean getConfiguredEmptySpaceAction() {
        return true;
      }

      @Override
      protected boolean getConfiguredSingleSelectionAction() {
        return false;
      }

      @Override
      protected void execAction() throws ProcessingException {
        CompanyForm form = new CompanyForm();
        form.startNew();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }
  }
}
