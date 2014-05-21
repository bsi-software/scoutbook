/**
 *
 */
package org.eclipse.scout.widget.client.ui.template.formfield;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widget.shared.ui.template.formfield.AbstractMonthsBoxData;

/**
 * @author mzi
 */
@FormData(value = AbstractMonthsBoxData.class, sdkCommand = FormData.SdkCommand.CREATE, defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractMonthsBox extends AbstractGroupBox {

  /**
   * @return the AprilField
   */
  public AprilField getAprilField() {
    return getFieldByClass(AprilField.class);
  }

  /**
   * @return the FebruaryField
   */
  public FebruaryField getFebruaryField() {
    return getFieldByClass(FebruaryField.class);
  }

  /**
   * @return the JanuaryField
   */
  public JanuaryField getJanuaryField() {
    return getFieldByClass(JanuaryField.class);
  }

  /**
   * @return the JuneField
   */
  public JuneField getJuneField() {
    return getFieldByClass(JuneField.class);
  }

  /**
   * @return the MarchField
   */
  public MarchField getMarchField() {
    return getFieldByClass(MarchField.class);
  }

  /**
   * @return the MayField
   */
  public MayField getMayField() {
    return getFieldByClass(MayField.class);
  }

  @Order(10.0)
  public class JanuaryField extends AbstractStringField {

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("January");
    }

    @Override
    protected int getConfiguredLabelWidthInPixel() {
      return 80;
    }
  }

  @Order(20.0)
  public class FebruaryField extends AbstractStringField {

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("February");
    }

    @Override
    protected int getConfiguredLabelWidthInPixel() {
      return 80;
    }
  }

  @Order(30.0)
  public class MarchField extends AbstractStringField {

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("March");
    }

    @Override
    protected int getConfiguredLabelWidthInPixel() {
      return 80;
    }
  }

  @Order(40.0)
  public class AprilField extends AbstractStringField {

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("April");
    }

    @Override
    protected int getConfiguredLabelWidthInPixel() {
      return 80;
    }
  }

  @Order(50.0)
  public class MayField extends AbstractStringField {

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("May");
    }

    @Override
    protected int getConfiguredLabelWidthInPixel() {
      return 80;
    }
  }

  @Order(60.0)
  public class JuneField extends AbstractStringField {

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("June");
    }

    @Override
    protected int getConfiguredLabelWidthInPixel() {
      return 80;
    }
  }

}