package org.eclipsescout.contacts.shared.ui.forms;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

public class CompanyFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public CompanyFormData() {
  }

  public CompanyIdProperty getCompanyIdProperty() {
    return getPropertyByClass(CompanyIdProperty.class);
  }

  /**
   * access method for property CompanyId.
   */
  public String getCompanyId() {
    return getCompanyIdProperty().getValue();
  }

  /**
   * access method for property CompanyId.
   */
  public void setCompanyId(String companyId) {
    getCompanyIdProperty().setValue(companyId);
  }

  public Location getLocation() {
    return getFieldByClass(Location.class);
  }

  public Name getName() {
    return getFieldByClass(Name.class);
  }

  public URL getURL() {
    return getFieldByClass(URL.class);
  }

  public class CompanyIdProperty extends AbstractPropertyData<String> {
    private static final long serialVersionUID = 1L;

    public CompanyIdProperty() {
    }
  }

  public static class Location extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Location() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class Name extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Name() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class URL extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public URL() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }
}
