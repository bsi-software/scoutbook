/**
 * 
 */
package org.eclipse.scout.transport.shared.services;

import java.util.Date;
import java.util.Map;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.transport.shared.services.lookup.EventLookupCall;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.formdata.FormDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class DesktopFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public DesktopFormData() {
  }

  public Event getEvent() {
    return getFieldByClass(Event.class);
  }

  public EventType getEventType() {
    return getFieldByClass(EventType.class);
  }

  public GetValue getGetValue() {
    return getFieldByClass(GetValue.class);
  }

  public ShowDatesFrom getShowDatesFrom() {
    return getFieldByClass(ShowDatesFrom.class);
  }

  public ShowDatesTo getShowDatesTo() {
    return getFieldByClass(ShowDatesTo.class);
  }

  public Value getValue() {
    return getFieldByClass(Value.class);
  }

  public static class Event extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public Event() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, EventLookupCall.class);
      ruleMap.put(ValidationRule.ZERO_NULL_EQUALITY, true);
    }
  }

  public static class EventType extends AbstractValueFieldData<Long> {

    private static final long serialVersionUID = 1L;

    public EventType() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      /**
       * XXX not processed ValidationRule(lookupCall)
       * 'EventTypeLookupCall.class' is not accessible from here.
       * at org.eclipse.scout.transport.client.ui.forms.DesktopForm.MainBox.DesktopBox.EventTypeField#
       * getConfiguredLookupCall
       */
      ruleMap.put(ValidationRule.ZERO_NULL_EQUALITY, true);
    }
  }

  public static class GetValue extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public GetValue() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MASTER_VALUE_FIELD, Event.class);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class ShowDatesFrom extends AbstractValueFieldData<Date> {

    private static final long serialVersionUID = 1L;

    public ShowDatesFrom() {
    }
  }

  public static class ShowDatesTo extends AbstractValueFieldData<Date> {

    private static final long serialVersionUID = 1L;

    public ShowDatesTo() {
    }
  }

  public static class Value extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public Value() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }
}