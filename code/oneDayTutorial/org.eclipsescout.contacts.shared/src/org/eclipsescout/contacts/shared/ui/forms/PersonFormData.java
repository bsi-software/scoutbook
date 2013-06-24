package org.eclipsescout.contacts.shared.ui.forms;

import java.util.Date;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;
import org.eclipsescout.contacts.shared.services.lookup.CompanyLookupCall;

public class PersonFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public PersonFormData() {
  }

  public PersonIdProperty getPersonIdProperty() {
    return getPropertyByClass(PersonIdProperty.class);
  }

  /**
   * access method for property PersonId.
   */
  public String getPersonId() {
    return getPersonIdProperty().getValue();
  }

  /**
   * access method for property PersonId.
   */
  public void setPersonId(String personId) {
    getPersonIdProperty().setValue(personId);
  }

  public Company getCompany() {
    return getFieldByClass(Company.class);
  }

  public DateOfBirth getDateOfBirth() {
    return getFieldByClass(DateOfBirth.class);
  }

  public FirstName getFirstName() {
    return getFieldByClass(FirstName.class);
  }

  public Headline getHeadline() {
    return getFieldByClass(Headline.class);
  }

  public LastName getLastName() {
    return getFieldByClass(LastName.class);
  }

  public Location getLocation() {
    return getFieldByClass(Location.class);
  }

  public PictureUrl getPictureUrl() {
    return getFieldByClass(PictureUrl.class);
  }

  public class PersonIdProperty extends AbstractPropertyData<String> {
    private static final long serialVersionUID = 1L;

    public PersonIdProperty() {
    }
  }

  public static class Company extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Company() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, CompanyLookupCall.class);
      ruleMap.put(ValidationRule.ZERO_NULL_EQUALITY, true);
    }
  }

  public static class DateOfBirth extends AbstractValueFieldData<Date> {
    private static final long serialVersionUID = 1L;

    public DateOfBirth() {
    }
  }

  public static class FirstName extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public FirstName() {
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

  public static class Headline extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Headline() {
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

  public static class LastName extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public LastName() {
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

  public static class PictureUrl extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public PictureUrl() {
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
