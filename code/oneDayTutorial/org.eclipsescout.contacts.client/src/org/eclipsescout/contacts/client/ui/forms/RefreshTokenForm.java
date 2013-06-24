package org.eclipsescout.contacts.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.shell.IShellService;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.contacts.client.ui.forms.RefreshTokenForm.MainBox.CancelButton;
import org.eclipsescout.contacts.client.ui.forms.RefreshTokenForm.MainBox.OkButton;
import org.eclipsescout.contacts.client.ui.forms.RefreshTokenForm.MainBox.OpenAuthURLButton;
import org.eclipsescout.contacts.client.ui.forms.RefreshTokenForm.MainBox.TokenBox;
import org.eclipsescout.contacts.client.ui.forms.RefreshTokenForm.MainBox.TokenBox.SecurityCodeField;
import org.eclipsescout.contacts.shared.services.ILinkedInService;

public class RefreshTokenForm extends AbstractForm {

  private String m_token;
  private String m_secret;

  public RefreshTokenForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("RefreshLinkedInToken");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public OpenAuthURLButton getOpenAuthURLButton() {
    return getFieldByClass(OpenAuthURLButton.class);
  }

  public SecurityCodeField getSecurityCodeField() {
    return getFieldByClass(SecurityCodeField.class);
  }

  public TokenBox getTokenBox() {
    return getFieldByClass(TokenBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class TokenBox extends AbstractGroupBox {

      @Order(10.0)
      public class SecurityCodeField extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("SecurityCode");
        }
      }
    }

    @Order(20.0)
    public class OpenAuthURLButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("OpenAuthURL");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        String[] auth = SERVICES.getService(ILinkedInService.class).getAuthUrl();
        setToken(auth[0]);
        setSecret(auth[1]);

        SERVICES.getService(IShellService.class).shellOpen(auth[2]);

        getSecurityCodeField().setEnabled(true);
      }
    }

    @Order(30.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(40.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class NewHandler extends AbstractFormHandler {
  }

  @FormData
  public String getToken() {
    return m_token;
  }

  @FormData
  public void setToken(String token) {
    m_token = token;
  }

  @FormData
  public String getSecret() {
    return m_secret;
  }

  @FormData
  public void setSecret(String secret) {
    m_secret = secret;
  }
}
