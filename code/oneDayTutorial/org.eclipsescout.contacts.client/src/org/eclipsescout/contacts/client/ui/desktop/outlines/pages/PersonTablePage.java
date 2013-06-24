package org.eclipsescout.contacts.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.contacts.client.ui.forms.PersonForm;
import org.eclipsescout.contacts.shared.Icons;
import org.eclipsescout.contacts.shared.services.IStandardOutlineService;

public class PersonTablePage extends AbstractPageWithTable<PersonTablePage.Table> {

  private String m_companyId;

  @Override
  protected String getConfiguredIconId() {
    return Icons.Eye;
  }

  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Person");
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    return SERVICES.getService(IStandardOutlineService.class).getPersonTableData(getCompanyId());
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    public LastNameColumn getLastNameColumn() {
      return getColumnSet().getColumnByClass(LastNameColumn.class);
    }

    public HeadlineColumn getHeadlineColumn() {
      return getColumnSet().getColumnByClass(HeadlineColumn.class);
    }

    @Override
    protected String getConfiguredDefaultIconId() {
      return Icons.Eye;
    }

    @Override
    protected void execRowAction(ITableRow row) throws ProcessingException {
      getMenu(EditPersonMenu.class).execAction();
    }

    public FirstNameColumn getFirstNameColumn() {
      return getColumnSet().getColumnByClass(FirstNameColumn.class);
    }

    public PersonIdColumn getPersonIdColumn() {
      return getColumnSet().getColumnByClass(PersonIdColumn.class);
    }

    @Order(10.0)
    public class PersonIdColumn extends AbstractStringColumn {

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
    public class FirstNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("FirstName");
      }
    }

    @Order(30.0)
    public class LastNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("LastName");
      }
    }

    @Order(40.0)
    public class HeadlineColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Headline");
      }
    }

    @Order(10.0)
    public class EditPersonMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("EditPerson");
      }

      @Override
      protected void execAction() throws ProcessingException {
        PersonForm form = new PersonForm();
        form.setPersonId(getPersonIdColumn().getSelectedValue());
        form.startModify();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(20.0)
    public class NewPersonMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("NewPerson");
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
        PersonForm form = new PersonForm();
        form.startNew();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }
  }

  @FormData
  public String getCompanyId() {
    return m_companyId;
  }

  @FormData
  public void setCompanyId(String companyId) {
    m_companyId = companyId;
  }
}
