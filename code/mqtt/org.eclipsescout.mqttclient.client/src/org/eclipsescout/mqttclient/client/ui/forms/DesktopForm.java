/**
 *
 */
package org.eclipsescout.mqttclient.client.ui.forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.scout.commons.CollectionUtility;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TreeMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.ValueFieldMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateTimeColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.mqttclient.client.services.MqttService;
import org.eclipsescout.mqttclient.client.services.UserPreferencesService;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.ClientBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.ClientBox.BrokerURLField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.ClientBox.ClientIdField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.LastWillAndTestamentBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.LastWillAndTestamentBox.WillMessageField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.LastWillAndTestamentBox.WillQoSField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.LastWillAndTestamentBox.WillRetainedField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.LastWillAndTestamentBox.WillTopicField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.PublishParametersBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.PublishParametersBox.DefaultQoSField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.PublishParametersBox.DefaultRetainedField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.PublishParametersBox.DefaultTopicField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.StatusBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.StatusBox.ConnectButton;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.StatusBox.DisconnectButton;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.StatusBox.StatusField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.UserBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.UserBox.CleanSessionField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.UserBox.ConnectionTimeoutField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.UserBox.HidePasswordField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.UserBox.MaskedPasswordField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.UserBox.PasswordField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox.UserBox.UserNameField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.MessagesBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.MessagesBox.MessagesField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.MessagesBox.PublishBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.MessagesBox.PublishBox.MessageField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.MessagesBox.PublishBox.PublishButton;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.MessagesBox.PublishBox.TopicField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.SubscriptionsBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.SubscriptionsBox.SubscribeBox;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.SubscriptionsBox.SubscribeBox.SubscribeButton;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.SubscriptionsBox.SubscribeBox.TopicFilterField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.SubscriptionsBox.SubscribeBox.TopicQoSField;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.SubscriptionsBox.SubscribeBox.UnsubscribeButton;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.SubscriptionsBox.SubscriptionsField;
import org.eclipsescout.mqttclient.shared.Icons;
import org.eclipsescout.mqttclient.shared.services.DesktopFormData;

/**
 * @author mzi
 */
@FormData(value = DesktopFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class DesktopForm extends AbstractForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public DesktopForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.EclipseScout;
  }

  /**
   * save current mqtt parameters to preferences
   */
  @Override
  protected void execDisposeForm() throws ProcessingException {
    UserPreferencesService prefs = SERVICES.getService(UserPreferencesService.class);

    if (prefs != null) {
      prefs.setBrokerUrl(getBrokerURLField().getValue());
      prefs.setClientId(getClientIdField().getValue());
      prefs.setUserName(getUserNameField().getValue());
      prefs.setPassword(getPasswordField().getValue());
      prefs.setConnectionTimeout(getConnectionTimeoutField().getValue().toString());
      prefs.setCleanSession(getCleanSessionField().getValue().toString());
      prefs.setWillTopic(getWillTopicField().getValue());
      prefs.setWillMessage(getWillMessageField().getValue());
      prefs.setWillQos(getWillQoSField().getValue().toString());
      prefs.setWillRetained(getWillRetainedField().getValue().toString());
      prefs.setDefaultTopic(getDefaultTopicField().getValue());
      prefs.setDefaultQos(getDefaultQoSField().getValue().toString());
      prefs.setDefaultRetained(getDefaultRetainedField().getValue().toString());

      prefs.store();
    }
  }

  /**
   * load mqtt parameters to preferences
   */
  @Override
  protected void execInitForm() throws ProcessingException {
    super.execInitForm();

    UserPreferencesService prefs = SERVICES.getService(UserPreferencesService.class);

    try {
      prefs.load();

      getBrokerURLField().setValue(prefs.getBrokerUrl());
      getClientIdField().setValue(prefs.getClientId());

      getUserNameField().setValue(prefs.getUserName());
      getPasswordField().setValue(prefs.getPassword());
      getConnectionTimeoutField().setValue(new Integer(prefs.getConnectionTimeout()));
      getCleanSessionField().setValue(new Boolean(prefs.getCleanSession()));

      getWillTopicField().setValue(prefs.getWillTopic());
      getWillMessageField().setValue(prefs.getWillMessage());
      getWillQoSField().setValue(new Integer(prefs.getWillQos()));
      getWillRetainedField().setValue(new Boolean(prefs.getWillRetained()));

      getDefaultTopicField().setValue(prefs.getDefaultTopic());
      getDefaultQoSField().setValue(new Integer(prefs.getDefaultQos()));
      getDefaultRetainedField().setValue(new Boolean(prefs.getDefaultRetained()));
    }
    catch (Exception e) {
      MessageBox.showOkMessage(TEXTS.get("MQTTClient"), TEXTS.get("PrefExeption"), e.getMessage());
    }

    getControlBox().setSelectedTab(getConnectionBox());
  }

  private DesktopFormData getFormData()
      throws ProcessingException
  {
    DesktopFormData formData = new DesktopFormData();
    exportFormData(formData);
    return formData;
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }

  /**
   * @return the BrokerURLField
   */
  public BrokerURLField getBrokerURLField() {
    return getFieldByClass(BrokerURLField.class);
  }

  /**
   * @return the CleanSessionField
   */
  public CleanSessionField getCleanSessionField() {
    return getFieldByClass(CleanSessionField.class);
  }

  /**
   * @return the ClientBox
   */
  public ClientBox getClientBox() {
    return getFieldByClass(ClientBox.class);
  }

  /**
   * @return the ClientIdField
   */
  public ClientIdField getClientIdField() {
    return getFieldByClass(ClientIdField.class);
  }

  /**
   * @return the ConnectButton
   */
  public ConnectButton getConnectButton() {
    return getFieldByClass(ConnectButton.class);
  }

  /**
   * @return the ConnectionBox
   */
  public ConnectionBox getConnectionBox() {
    return getFieldByClass(ConnectionBox.class);
  }

  /**
   * @return the ConnectionTimeoutField
   */
  public ConnectionTimeoutField getConnectionTimeoutField() {
    return getFieldByClass(ConnectionTimeoutField.class);
  }

  /**
   * @return the ControlBox
   */
  public ControlBox getControlBox() {
    return getFieldByClass(ControlBox.class);
  }

  /**
   * @return the DisconnectButton
   */
  public DisconnectButton getDisconnectButton() {
    return getFieldByClass(DisconnectButton.class);
  }

  /**
   * @return the HidePasswordField
   */
  public HidePasswordField getHidePasswordField() {
    return getFieldByClass(HidePasswordField.class);
  }

  /**
   * @return the LastWillAndTestamentBox
   */
  public LastWillAndTestamentBox getLastWillAndTestamentBox() {
    return getFieldByClass(LastWillAndTestamentBox.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the WillMessageField
   */
  public WillMessageField getWillMessageField() {
    return getFieldByClass(WillMessageField.class);
  }

  /**
   * @return the UnsubscribeButton
   */
  public UnsubscribeButton getUnsubscribeButton() {
    return getFieldByClass(UnsubscribeButton.class);
  }

  /**
   * @return the UserBox
   */
  public UserBox getUserBox() {
    return getFieldByClass(UserBox.class);
  }

  /**
   * @return the MessagesBox
   */
  public MessagesBox getMessagesBox() {
    return getFieldByClass(MessagesBox.class);
  }

  /**
   * @return the MessagesField
   */
  public MessagesField getMessagesField() {
    return getFieldByClass(MessagesField.class);
  }

  /**
   * @return the PasswordField
   */
  public PasswordField getPasswordField() {
    return getFieldByClass(PasswordField.class);
  }

  /**
   * @return the WillQoSField
   */
  public WillQoSField getWillQoSField() {
    return getFieldByClass(WillQoSField.class);
  }

  /**
   * @return the WillRetainedField
   */
  public WillRetainedField getWillRetainedField() {
    return getFieldByClass(WillRetainedField.class);
  }

  /**
   * @return the WillTopicField
   */
  public WillTopicField getWillTopicField() {
    return getFieldByClass(WillTopicField.class);
  }

  /**
   * @return the PublishParametersBox
   */
  public PublishParametersBox getPublishParametersBox() {
    return getFieldByClass(PublishParametersBox.class);
  }

  /**
   * @return the DefaultQoSField
   */
  public DefaultQoSField getDefaultQoSField() {
    return getFieldByClass(DefaultQoSField.class);
  }

  /**
   * @return the MaskedPasswordField
   */
  public MaskedPasswordField getMaskedPasswordField() {
    return getFieldByClass(MaskedPasswordField.class);
  }

  /**
   * @return the MessageField
   */
  public MessageField getMessageField() {
    return getFieldByClass(MessageField.class);
  }

  /**
   * @return the PublishBox
   */
  public PublishBox getPublishBox() {
    return getFieldByClass(PublishBox.class);
  }

  /**
   * @return the PublishButton
   */
  public PublishButton getPublishButton() {
    return getFieldByClass(PublishButton.class);
  }

  /**
   * @return the SubscribeBox
   */
  public SubscribeBox getSubscribeBox() {
    return getFieldByClass(SubscribeBox.class);
  }

  /**
   * @return the TopicField
   */
  public TopicField getTopicField() {
    return getFieldByClass(TopicField.class);
  }

  /**
   * @return the DefaultRetainedField
   */
  public DefaultRetainedField getDefaultRetainedField() {
    return getFieldByClass(DefaultRetainedField.class);
  }

  /**
   * @return the StatusBox
   */
  public StatusBox getStatusBox() {
    return getFieldByClass(StatusBox.class);
  }

  /**
   * @return the StatusField
   */
  public StatusField getStatusField() {
    return getFieldByClass(StatusField.class);
  }

  /**
   * @return the SubscriptionsBox
   */
  public SubscriptionsBox getSubscriptionsBox() {
    return getFieldByClass(SubscriptionsBox.class);
  }

  /**
   * @return the SubscribeButton
   */
  public SubscribeButton getSubscribeButton() {
    return getFieldByClass(SubscribeButton.class);
  }

  /**
   * @return the SubscriptionsField
   */
  public SubscriptionsField getSubscriptionsField() {
    return getFieldByClass(SubscriptionsField.class);
  }

  /**
   * @return the DefaultTopicField
   */
  public DefaultTopicField getDefaultTopicField() {
    return getFieldByClass(DefaultTopicField.class);
  }

  /**
   * @return the TopicFilterField
   */
  public TopicFilterField getTopicFilterField() {
    return getFieldByClass(TopicFilterField.class);
  }

  /**
   * @return the TopicQoSField
   */
  public TopicQoSField getTopicQoSField() {
    return getFieldByClass(TopicQoSField.class);
  }

  /**
   * @return the UserNameField
   */
  public UserNameField getUserNameField() {
    return getFieldByClass(UserNameField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredLabelVisible() {
      return false;
    }

    @Order(20.0)
    public class ControlBox extends AbstractTabBox {

      @Override
      protected void execInitField() throws ProcessingException {
        //TODO [mzi] Auto-generated method stub.
        super.execInitField();
      }

      @Order(10.0)
      public class MessagesBox extends AbstractGroupBox {

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Messages");
        }

        @Order(20.0)
        public class MessagesField extends AbstractTableField<MessagesField.Table> {

          @Override
          protected int getConfiguredGridH() {
            return 5;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Order(10.0)
          public class Table extends AbstractExtensibleTable {

            /**
             * @return the TopicColumn
             */
            public TopicColumn getTopicColumn() {
              return getColumnSet().getColumnByClass(TopicColumn.class);
            }

            /**
             * @return the QoSColumn
             */
            public QoSColumn getQoSColumn() {
              return getColumnSet().getColumnByClass(QoSColumn.class);
            }

            /**
             * @return the RetainedColumn
             */
            public RetainedColumn getRetainedColumn() {
              return getColumnSet().getColumnByClass(RetainedColumn.class);
            }

            @Override
            protected void execRowAction(ITableRow row) throws ProcessingException {
              getMenu(ReplyMenu.class).execAction();
              getMessageField().requestFocus();
            }

            /**
             * @return the MessageColumn
             */
            public MessageColumn getMessageColumn() {
              return getColumnSet().getColumnByClass(MessageColumn.class);
            }

            /**
             * @return the ReceivedColumn
             */
            public ReceivedColumn getReceivedColumn() {
              return getColumnSet().getColumnByClass(ReceivedColumn.class);
            }

            @Order(10.0)
            public class MessageColumn extends AbstractStringColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Message");
              }

              @Override
              protected int getConfiguredWidth() {
                return 400;
              }
            }

            @Order(20.0)
            public class TopicColumn extends AbstractStringColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Topic");
              }

              @Override
              protected int getConfiguredWidth() {
                return 250;
              }
            }

            @Order(30.0)
            public class ReceivedColumn extends AbstractDateTimeColumn {

              @Override
              protected String getConfiguredFormat() {
                return "HH:mm:ss dd.MM.yyyy";
              }

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Received");
              }

              @Override
              protected boolean getConfiguredSortAscending() {
                return false;
              }

              @Override
              protected int getConfiguredSortIndex() {
                return 1;
              }

              @Override
              protected int getConfiguredWidth() {
                return 120;
              }
            }

            @Order(40.0)
            public class QoSColumn extends AbstractIntegerColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("QoS");
              }

              @Override
              protected int getConfiguredWidth() {
                return 40;
              }
            }

            @Order(50.0)
            public class RetainedColumn extends AbstractBooleanColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Retained");
              }
            }

            @Order(20.0)
            public class ClearMessageLogMenu extends AbstractExtensibleMenu {

              @Override
              protected Set<? extends IMenuType> getConfiguredMenuTypes() {
                return CollectionUtility.<IMenuType> hashSet(TableMenuType.EmptySpace, TableMenuType.MultiSelection, TableMenuType.SingleSelection, ValueFieldMenuType.NotNull, TreeMenuType.EmptySpace, TreeMenuType.MultiSelection, TreeMenuType.SingleSelection);
              }

              @Override
              protected String getConfiguredText() {
                return TEXTS.get("ClearMessageLog");
              }

              @Override
              protected void execAction() throws ProcessingException {
                getTable().deleteAllRows();
              }
            }

            @Order(10.0)
            public class ReplyMenu extends AbstractExtensibleMenu {

              @Override
              protected String getConfiguredText() {
                return TEXTS.get("Reply");
              }

              @Override
              protected void execAction() throws ProcessingException {
                ITableRow selectedRow = getTable().getSelectedRow();
                String message = getTable().getMessageColumn().getValue(selectedRow);
                String topic = getTable().getTopicColumn().getValue(selectedRow);

                getMessageField().setValue(TEXTS.get("ReplyPrefix") + message);
                getTopicField().setValue(topic);
              }
            }
          }
        }

        @Order(10.0)
        public class PublishBox extends AbstractSequenceBox {

          @Override
          protected boolean getConfiguredAutoCheckFromTo() {
            return false;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Order(10.0)
          public class MessageField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Message");
            }

            @Override
            protected int getConfiguredLabelPosition() {
              return LABEL_POSITION_ON_FIELD;
            }

            @Override
            protected void execChangedValue() throws ProcessingException {
              getPublishButton().setEnabled(!StringUtility.isNullOrEmpty(getValue()));
            }
          }

          @Order(20.0)
          public class TopicField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Topic");
            }

            @Override
            protected int getConfiguredLabelPosition() {
              return LABEL_POSITION_ON_FIELD;
            }
          }

          @Order(30.0)
          public class PublishButton extends AbstractButton {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Publish");
            }

            @Override
            protected void execClickAction() throws ProcessingException {
              AbstractStringField topic = getTopicField();
              AbstractStringField message = getMessageField();
              AbstractIntegerField qos = getDefaultQoSField();
              AbstractBooleanField retained = getDefaultRetainedField();

              // use default topic if the publish topic is empty
              if (StringUtility.isNullOrEmpty(topic.getValue())) {
                topic.setValue(getDefaultTopicField().getValue());
              }

              if (publishFieldsAreValid(topic, message, qos)) {
                getMqttService().publish(
                    topic.getValue(),
                    message.getValue(),
                    qos.getValue(),
                    retained.getValue()
                    );
              }
            }
          }
        }

        @Order(10.0)
        public class EnterKeyStroke extends AbstractKeyStroke {

          @Override
          protected String getConfiguredKeyStroke() {
            return "ENTER";
          }

          @Override
          protected void execAction() throws ProcessingException {
            if (getPublishButton().isEnabled()) {
              getPublishButton().execClickAction();
            }
          }
        }
      }

      @Order(30.0)
      public class ConnectionBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Connection");
        }

        public void updateClientFields() throws ProcessingException {
          boolean connected = getMqttService().isConnected();

          getBrokerURLField().setEnabled(!connected);
          getClientIdField().setEnabled(!connected);
        }

        public void updateConnectionFields() throws ProcessingException {
          boolean connected = getMqttService().isConnected();

          getUserBox().setEnabled(!connected);
          getHidePasswordField().setEnabled(true);
          getLastWillAndTestamentBox().setEnabled(!connected);
        }

        public void updateConnectionStatus() throws ProcessingException {
          boolean connected = getMqttService().isConnected();

          if (connected) {
            getStatusField().setValue(TEXTS.get("Connected"));
          }
          else {
            getStatusField().setValue(TEXTS.get("Disconnected"));
          }

          getConnectButton().setVisible(!connected);
          getConnectButton().setEnabled(!connected);
          getDisconnectButton().setVisible(connected);
          getDisconnectButton().setEnabled(connected);
        }

        @Order(20.0)
        public class UserBox extends AbstractGroupBox {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Connection");
          }

          @Override
          protected void execInitField() throws ProcessingException {
            updatePasswordVisibility();
          }

          private void updatePasswordVisibility() {
            boolean hidePassword = getHidePasswordField().getValue();

            if (hidePassword) {
              getMaskedPasswordField().setValue(getPasswordField().getValue());
            }
            else {
              getPasswordField().setValue(getMaskedPasswordField().getValue());
            }

            getMaskedPasswordField().setVisible(hidePassword);
            getPasswordField().setVisible(!hidePassword);
          }

          @Order(10.0)
          public class UserNameField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("UserName");
            }
          }

          @Order(20.0)
          public class PasswordField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Password");
            }
          }

          @Order(30.0)
          public class MaskedPasswordField extends AbstractStringField {

            @Override
            protected boolean getConfiguredInputMasked() {
              return true;
            }

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Password");
            }
          }

          @Order(40.0)
          public class ConnectionTimeoutField extends AbstractIntegerField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("ConnectionTimeout");
            }
          }

          @Order(50.0)
          public class CleanSessionField extends AbstractCheckBox {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("CleanSession");
            }
          }

          @Order(60.0)
          public class HidePasswordField extends AbstractCheckBox {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("HidePassword");
            }

            @Override
            protected void execChangedValue() throws ProcessingException {
              updatePasswordVisibility();
            }
          }

        }

        @Order(30.0)
        public class LastWillAndTestamentBox extends AbstractGroupBox {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("LastWillAndTestament");
          }

          @Order(10.0)
          public class WillTopicField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Topic");
            }
          }

          @Order(20.0)
          public class WillMessageField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Message");
            }
          }

          @Order(30.0)
          public class WillQoSField extends AbstractIntegerField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("QoS");
            }
          }

          @Order(40.0)
          public class WillRetainedField extends AbstractCheckBox {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Retained");
            }
          }
        }

        @Order(10.0)
        public class ClientBox extends AbstractGroupBox {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Client");
          }

          @Order(50.0)
          public class BrokerURLField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("BrokerURL");
            }
          }

          @Order(60.0)
          public class ClientIdField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("ClientId");
            }
          }
        }

        @Order(40.0)
        public class PublishParametersBox extends AbstractGroupBox {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("PublishParameters");
          }

          @Order(10.0)
          public class DefaultTopicField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("DefaultTopic");
            }

            @Override
            protected String execValidateValue(String rawValue) throws ProcessingException {
              if (StringUtility.isNullOrEmpty(rawValue)) {
                throw new ProcessingException(TEXTS.get("topicFieldEmpty"));
              }

              return rawValue;
            }
          }

          @Order(20.0)
          public class DefaultQoSField extends AbstractIntegerField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("QoS");
            }

            @Override
            protected Integer getConfiguredMaxValue() {
              return 2;
            }

            @Override
            protected Integer getConfiguredMinValue() {
              return 0;
            }
          }

          @Order(30.0)
          public class DefaultRetainedField extends AbstractCheckBox {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Retained");
            }
          }
        }

        @Order(5.0)
        public class StatusBox extends AbstractSequenceBox {

          @Override
          protected boolean getConfiguredAutoCheckFromTo() {
            return false;
          }

          @Override
          protected int getConfiguredGridW() {
            return 2;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execInitField() throws ProcessingException {
            updateConnectionStatus();
          }

          @Order(20.0)
          public class StatusField extends AbstractStringField {

            @Override
            protected boolean getConfiguredEnabled() {
              return false;
            }

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }

          @Order(30.0)
          public class ConnectButton extends AbstractButton {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Connect");
            }

            @Override
            protected void execClickAction() throws ProcessingException {
              getMqttService().setup(
                  getBrokerURLField().getValue(),
                  getClientIdField().getValue()
                  );

              getMqttService().connect(
                  getUserNameField().getValue(),
                  getPasswordField().getValue(),
                  getCleanSessionField().getValue(),
                  getConnectionTimeoutField().getValue(),
                  getWillTopicField().getValue(),
                  getWillMessageField().getValue(),
                  getWillQoSField().getValue(),
                  getWillRetainedField().getValue()
                  );

              updateClientFields();
              updateConnectionFields();
              updateConnectionStatus();
            }
          }

          @Order(40.0)
          public class DisconnectButton extends AbstractButton {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Disconnect");
            }

            @Override
            protected void execClickAction() throws ProcessingException {
              getMqttService().disconnect();

              updateClientFields();
              updateConnectionFields();
              updateConnectionStatus();
            }
          }
        }
      }

      @Order(20.0)
      public class SubscriptionsBox extends AbstractGroupBox {

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Subscriptions");
        }

        @Order(10.0)
        public class SubscribeBox extends AbstractSequenceBox {

          @Override
          protected boolean getConfiguredAutoCheckFromTo() {
            return false;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execInitField() throws ProcessingException {
            getPublishButton().setEnabled(false);
          }

          @Order(10.0)
          public class TopicFilterField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("TopicFilter");
            }

            @Override
            protected int getConfiguredLabelPosition() {
              return LABEL_POSITION_ON_FIELD;
            }

            @Override
            protected void execChangedValue() throws ProcessingException {
              String topic = getValue();

              if (StringUtility.isNullOrEmpty(topic)) {
                getSubscribeButton().setEnabled(false);
                getUnsubscribeButton().setEnabled(false);
                return;
              }

              Iterator<ITableRow> topics = getSubscriptionsField().getTable().getRows().iterator();
              boolean newTopic = true;

              while (topics.hasNext()) {
                String rowTopic = (String) topics.next().getKeyValues().get(0);
                if (rowTopic.equals(topic)) {
                  newTopic = false;
                  break;
                }
              }

              getSubscribeButton().setEnabled(newTopic);
              getUnsubscribeButton().setEnabled(true);
            }
          }

          @Order(20.0)
          public class TopicQoSField extends AbstractIntegerField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("QoS");
            }

            @Override
            protected int getConfiguredLabelPosition() {
              return LABEL_POSITION_ON_FIELD;
            }

            @Override
            protected Integer getConfiguredMaxValue() {
              return 2;
            }

            @Override
            protected Integer getConfiguredMinValue() {
              return 0;
            }
          }

          @Order(30.0)
          public class SubscribeButton extends AbstractButton {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Subscribe");
            }

            @Override
            protected void execClickAction() throws ProcessingException {
              String topic = getTopicFilterField().getValue();
              Integer qos = getTopicQoSField().getValue();

              getMqttService().subscribe(topic, qos);
              getSubscribeButton().setEnabled(false);
              getUnsubscribeButton().setEnabled(true);
              addTopicRow(topic, qos);
            }
          }

          @Order(40.0)
          public class UnsubscribeButton extends AbstractButton {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Unsubscribe");
            }

            @Override
            protected void execClickAction() throws ProcessingException {
              String topic = getTopicFilterField().getValue();

              getMqttService().unsubscribe(topic);
              getSubscribeButton().setEnabled(true);
              getUnsubscribeButton().setEnabled(false);
              removeTopicRow(topic);
            }
          }
        }

        @Order(40.0)
        public class SubscriptionsField extends AbstractTableField<SubscriptionsField.Table> {

          @Override
          protected int getConfiguredGridH() {
            return 7;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Order(10.0)
          public class Table extends AbstractExtensibleTable {

            @Override
            protected void execRowsSelected(List<? extends ITableRow> rows) throws ProcessingException {
              if (rows.size() == 1) {
                getTopicFilterField().setValue((String) rows.get(0).getCellValue(0));
                getTopicQoSField().setValue((Integer) rows.get(0).getCellValue(1));
              }
              else {
                getTopicFilterField().setValue(null);
                getTopicQoSField().setValue(null);
              }
            }

            /**
             * @return the QoSColumn
             */
            public QoSColumn getQoSColumn() {
              return getColumnSet().getColumnByClass(QoSColumn.class);
            }

            /**
             * @return the TopicFilterColumn
             */
            public TopicFilterColumn getTopicFilterColumn() {
              return getColumnSet().getColumnByClass(TopicFilterColumn.class);
            }

            @Order(10.0)
            public class TopicFilterColumn extends AbstractStringColumn {

              @Override
              protected boolean getConfiguredAutoOptimizeWidth() {
                return true;
              }

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("TopicFilter");
              }

              @Override
              protected boolean getConfiguredPrimaryKey() {
                return true;
              }

              @Override
              protected int getConfiguredWidth() {
                return 350;
              }
            }

            @Order(20.0)
            public class QoSColumn extends AbstractIntegerColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("QoS");
              }
            }

            @Order(10.0)
            public class UnsubscribeMenu extends AbstractExtensibleMenu {

              @Override
              protected String getConfiguredText() {
                return TEXTS.get("Unsubscribe");
              }

              @Override
              protected void execAction() throws ProcessingException {
                getUnsubscribeButton().execClickAction();
              }
            }
          }
        }

        @Order(10.0)
        public class EnterKeyStroke extends AbstractKeyStroke {

          @Override
          protected String getConfiguredKeyStroke() {
            return "ENTER";
          }

          @Override
          protected void execAction() throws ProcessingException {
            if (getSubscribeButton().isEnabled()) {
              getSubscribeButton().execClickAction();
            }
          }
        }
      }
    }

    private ITableRow getTopicRow(String topic) {
      List<String> topicKey = new ArrayList<>();
      ITableRow row = null;

      topicKey.add(topic);
      row = getSubscriptionsField().getTable().findRowByKey(topicKey);

      return row;
    }

    private void addTopicRow(String topic, Integer qos) throws ProcessingException {
      ITableRow row = getTopicRow(topic);

      if (row == null) {
        getSubscriptionsField().getTable().addRowByArray(new Object[]{topic, qos});
      }
    }

    private void removeTopicRow(String topic) throws ProcessingException {
      ITableRow row = getTopicRow(topic);

      if (row != null) {
        getSubscriptionsField().getTable().deleteRow(row);
      }
    }
  }

  /**
   * validate fields necessary to publish a message.
   * sets/clears error stati accordingly.
   *
   * @return true: field content ok to publish a message, false otherwise
   */
  private boolean publishFieldsAreValid(AbstractStringField message, AbstractStringField topic) {
    return publishFieldsAreValid(message, topic, null);
  }

  private boolean publishFieldsAreValid(
      AbstractStringField topic,
      AbstractStringField message,
      AbstractIntegerField qos)
  {
    boolean valid = true;

    if (StringUtility.isNullOrEmpty(message.getValue())) {
      message.setErrorStatus(TEXTS.get("MessageIsEmpty"));
      valid = false;
    }
    else {
      message.clearErrorStatus();
    }

    if (StringUtility.isNullOrEmpty(topic.getValue())) {
      topic.setErrorStatus(TEXTS.get("TopicIsEmpty"));
      valid = false;
    }
    else {
      topic.clearErrorStatus();
    }

    if (qos != null && qos.getErrorStatus() != null) {
      valid = false;
    }

    return valid;
  }

  private MqttService getMqttService() {
    MqttService service = SERVICES.getService(MqttService.class);
    return service;
  }

  public class ViewHandler extends AbstractFormHandler {
  }
}
