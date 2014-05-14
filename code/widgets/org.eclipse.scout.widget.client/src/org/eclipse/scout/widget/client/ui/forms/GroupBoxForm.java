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

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.IGroupBoxBodyGrid;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.internal.HorizontalGroupBoxBodyGrid;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.internal.VerticalSmartGroupBoxBodyGrid;
import org.eclipse.scout.rt.client.ui.form.fields.placeholder.AbstractPlaceholderField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.CloseButton;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ConfigurationBox.Config1Box;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ConfigurationBox.Config1Box.ScrollableBox;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ConfigurationBox.Config1Box.SectionBox;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ConfigurationBox.Config2Box;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ConfigurationBox.Config2Box.ExpandedField;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ConfigurationBox.Config2Box.Placeholder1Field;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example1Box;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example1Box.DefaultBox;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example1Box.DefaultBox.CommentField;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example1Box.DefaultBox.CompanyField;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example1Box.DefaultBox.FirstNameField;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example1Box.DefaultBox.LastNameField;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example1Box.SingleColumnBox;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example1Box.SingleColumnBox.CityField;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example1Box.SingleColumnBox.CountryField;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example2Box;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example2Box.HorizontalMonthsBox;
import org.eclipse.scout.widget.client.ui.forms.GroupBoxForm.MainBox.ExamplesBox.Example2Box.VerticalMonthsBox;
import org.eclipse.scout.widget.client.ui.template.formfield.AbstractMonthsBox;

public class GroupBoxForm extends AbstractForm implements IPageForm {

  public GroupBoxForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("GroupBox");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  /**
   * @return the CityField
   */
  public CityField getCityField() {
    return getFieldByClass(CityField.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  /**
   * @return the CountryField
   */
  public CountryField getCountryField() {
    return getFieldByClass(CountryField.class);
  }

  /**
   * @return the DefaultBox
   */
  public DefaultBox getDefaultBox() {
    return getFieldByClass(DefaultBox.class);
  }

  /**
   * @return the Example1Box
   */
  public Example1Box getExample1Box() {
    return getFieldByClass(Example1Box.class);
  }

  /**
   * @return the Example2Box
   */
  public Example2Box getExample2Box() {
    return getFieldByClass(Example2Box.class);
  }

  /**
   * @return the Config1Box
   */
  public Config1Box getConfig1Box() {
    return getFieldByClass(Config1Box.class);
  }

  public ExamplesBox getExamplesBox() {
    return getFieldByClass(ExamplesBox.class);
  }

  /**
   * @return the ExpandedField
   */
  public ExpandedField getExpandedField() {
    return getFieldByClass(ExpandedField.class);
  }

  /**
   * @return the FirstNameField
   */
  public FirstNameField getFirstNameField() {
    return getFieldByClass(FirstNameField.class);
  }

  /**
   * @return the HorizontalMonthsBox
   */
  public HorizontalMonthsBox getHorizontalMonthsBox() {
    return getFieldByClass(HorizontalMonthsBox.class);
  }

  /**
   * @return the LastNameField
   */
  public LastNameField getLastNameField() {
    return getFieldByClass(LastNameField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the CommentField
   */
  public CommentField getCommentField() {
    return getFieldByClass(CommentField.class);
  }

  /**
   * @return the CompanyField
   */
  public CompanyField getCompanyField() {
    return getFieldByClass(CompanyField.class);
  }

  /**
   * @return the Config2Box
   */
  public Config2Box getConfig2Box() {
    return getFieldByClass(Config2Box.class);
  }

  public ConfigurationBox getConfigurationBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  /**
   * @return the Placeholder1Field
   */
  public Placeholder1Field getPlaceholder1Field(){
    return getFieldByClass(Placeholder1Field.class);
  }

  /**
   * @return the ScrollableBox
   */
  public ScrollableBox getScrollableBox() {
    return getFieldByClass(ScrollableBox.class);
  }

  /**
   * @return the SectionBox
   */
  public SectionBox getSectionBox() {
    return getFieldByClass(SectionBox.class);
  }

  /**
   * @return the SingleColumnBox
   */
  public SingleColumnBox getSingleColumnBox() {
    return getFieldByClass(SingleColumnBox.class);
  }

  /**
   * @return the VerticalMonthsBox
   */
  public VerticalMonthsBox getVerticalMonthsBox() {
    return getFieldByClass(VerticalMonthsBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(20.0)
    public class ExamplesBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 5;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Examples");
      }

      @Order(10.0)
      public class Example1Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 5;
        }

        @Order(10.0)
        public class DefaultBox extends AbstractGroupBox {

          @Override
          protected int getConfiguredGridColumnCount() {
            return 2;
          }

          @Override
          protected int getConfiguredGridW() {
            return 3;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Default");
          }

          @Order(10.0)
          public class FirstNameField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("FirstName");
            }
          }

          @Order(20.0)
          public class LastNameField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("LastName");
            }
          }

          @Order(30.0)
          public class CompanyField extends AbstractStringField {

            @Override
            protected int getConfiguredGridH() {
              return 2;
            }

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Company");
            }

            @Override
            protected boolean getConfiguredMultilineText() {
              return true;
            }
          }

          @Order(50.0)
          public class CommentField extends AbstractStringField {

            @Override
            protected int getConfiguredGridW() {
              return 2;
            }

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Comments");
            }
          }
        }

        @Order(20.0)
        public class SingleColumnBox extends AbstractGroupBox {

          @Override
          protected int getConfiguredGridColumnCount() {
            return 1;
          }

          @Override
          protected int getConfiguredGridW() {
            return 2;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("SingleColumnBox");
          }

          @Order(10.0)
          public class CityField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("City");
            }
          }

          @Order(20.0)
          public class CountryField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Country");
            }
          }
        }
      }

      @Order(20.0)
      public class Example2Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 5;
        }

        @Order(10.0)
        public class VerticalMonthsBox extends AbstractMonthsBox {
          @Override
          protected Class<? extends IGroupBoxBodyGrid> getConfiguredBodyGrid() {
            return VerticalSmartGroupBoxBodyGrid.class;
          }

          @Override
          protected int getConfiguredGridColumnCount() {
            return 2;
          }

          @Override
          protected int getConfiguredGridW() {
            return 2;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("VertcalLayout");
          }
        }

        @Order(20.0)
        public class HorizontalMonthsBox extends AbstractMonthsBox {
          @Override
          protected Class<? extends IGroupBoxBodyGrid> getConfiguredBodyGrid() {
            return HorizontalGroupBoxBodyGrid.class;
          }

          @Override
          protected int getConfiguredGridColumnCount() {
            return 3;
          }

          @Override
          protected int getConfiguredGridW() {
            return 3;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("HorizontalLayout");
          }
        }

        //        @Order(20.0)
        //        public class HorizontalMonthsBox extends AbstractGroupBox {
        //
        //          @Override
        //          protected Class<? extends IGroupBoxBodyGrid> getConfiguredBodyGrid() {
        //            return HorizontalGroupBoxBodyGrid.class;
        //          }
        //
        //          @Override
        //          protected int getConfiguredGridColumnCount() {
        //            return 3;
        //          }
        //
        //          @Override
        //          protected int getConfiguredGridW() {
        //            return 3;
        //          }
        //
        //          @Override
        //          protected String getConfiguredLabel() {
        //            return TEXTS.get("HorizontalLayout");
        //          }
        //
        //          @Order(10.0)
        //          public class JanuaryField extends AbstractStringField {
        //
        //            @Override
        //            protected String getConfiguredLabel() {
        //              return TEXTS.get("January");
        //            }
        //
        //            @Override
        //            protected int getConfiguredLabelWidthInPixel() {
        //              return 80;
        //            }
        //          }
        //
        //          @Order(20.0)
        //          public class FebruaryField extends AbstractStringField {
        //
        //            @Override
        //            protected String getConfiguredLabel() {
        //              return TEXTS.get("February");
        //            }
        //
        //            @Override
        //            protected int getConfiguredLabelWidthInPixel() {
        //              return 80;
        //            }
        //          }
        //
        //          @Order(30.0)
        //          public class MarchField extends AbstractStringField {
        //
        //            @Override
        //            protected String getConfiguredLabel() {
        //              return TEXTS.get("March");
        //            }
        //
        //            @Override
        //            protected int getConfiguredLabelWidthInPixel() {
        //              return 80;
        //            }
        //          }
        //
        //          @Order(40.0)
        //          public class AprilField extends AbstractStringField {
        //
        //            @Override
        //            protected String getConfiguredLabel() {
        //              return TEXTS.get("April");
        //            }
        //
        //            @Override
        //            protected int getConfiguredLabelWidthInPixel() {
        //              return 80;
        //            }
        //          }
        //
        //          @Order(50.0)
        //          public class MayField extends AbstractStringField {
        //
        //            @Override
        //            protected String getConfiguredLabel() {
        //              return TEXTS.get("May");
        //            }
        //
        //            @Override
        //            protected int getConfiguredLabelWidthInPixel() {
        //              return 80;
        //            }
        //          }
        //
        //          @Order(60.0)
        //          public class JuneField extends AbstractStringField {
        //
        //            @Override
        //            protected String getConfiguredLabel() {
        //              return TEXTS.get("June");
        //            }
        //
        //            @Override
        //            protected int getConfiguredLabelWidthInPixel() {
        //              return 80;
        //            }
        //          }
        //        }
      }

    }

    @Order(30.0)
    public class ConfigurationBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Configure");
      }

      @Order(10.0)
      public class Config1Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 5;
        }

        @Order(20.0)
        public class SectionBox extends AbstractMonthsBox {

          @Override
          protected String getConfiguredBorderDecoration() {
            return BORDER_DECORATION_SECTION;
          }

          @Override
          protected boolean getConfiguredExpandable() {
            return true;
          }

          @Override
          protected int getConfiguredGridW() {
            return 3;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Section");
          }
        }

        @Order(30.0)
        public class ScrollableBox extends AbstractMonthsBox {

          @Override
          protected int getConfiguredGridColumnCount() {
            return 1;
          }

          @Override
          protected boolean getConfiguredGridUseUiHeight() {
            return false;
          }

          @Override
          protected int getConfiguredGridW() {
            return 2;
          }

          @Override
          protected int getConfiguredHeightInPixel() {
            return 150;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Scrollable");
          }

          @Override
          protected boolean getConfiguredScrollable() {
            return true;
          }
        }
      }

      @Order(20.0)
      public class Config2Box extends AbstractGroupBox {

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Order(10.0)
        public class ExpandedField extends AbstractCheckBox {

          @Override
          protected String getConfiguredFont() {
            return "ITALIC";
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Expanded");
          }

          @Override
          protected void execChangedValue() throws ProcessingException {
            getSectionBox().setExpanded(getValue());
          }

          @Override
          protected void execInitField() throws ProcessingException {
            setValue(getSectionBox().isExpanded());
          }
        }

        @Order(20.0)
        public class Placeholder1Field extends AbstractPlaceholderField {

          @Override
          protected int getConfiguredGridH() {
            return 2;
          }
        }

        @Order(30.0)
        public class ScrollableField extends AbstractCheckBox {

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected String getConfiguredFont() {
            return "ITALIC";
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Scrollable");
          }

          @Override
          protected void execInitField() throws ProcessingException {
            setValue(getScrollableBox().isScrollable());
          }
        }

        @Order(40.0)
        public class VisibleField extends AbstractCheckBox {

          @Override
          protected String getConfiguredFont() {
            return "ITALIC";
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Visible");
          }

          @Override
          protected void execChangedValue() throws ProcessingException {
            getScrollableBox().setVisible(getValue());
          }

          @Override
          protected void execInitField() throws ProcessingException {
            setValue(getScrollableBox().isVisible());
          }
        }
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
