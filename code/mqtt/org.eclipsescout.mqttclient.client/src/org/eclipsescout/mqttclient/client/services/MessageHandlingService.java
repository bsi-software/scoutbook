/**
 *
 */
package org.eclipsescout.mqttclient.client.services;

import java.util.Date;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.AbstractService;
import org.eclipsescout.mqttclient.client.ui.desktop.Desktop;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm;
import org.eclipsescout.mqttclient.client.ui.forms.DesktopForm.MainBox.ControlBox.ConnectionBox;

/**
 * @author mzi
 */
public class MessageHandlingService extends AbstractService implements IMessageHandlingService {

  @Override
  public void handleMessage(String topic, String message, int qos, boolean retained, Date received) throws ProcessingException {
    DesktopForm desktopForm = getDesktopForm();

    if (desktopForm != null) {
      AbstractTable table = desktopForm.getMessagesField().getTable();
      table.addRowByArray(new Object[]{message, topic, received, qos, retained});
    }
  }

  @Override
  public void handleDisconnect() throws ProcessingException {
    DesktopForm desktopForm = getDesktopForm();

    if (desktopForm != null) {
      ConnectionBox box = desktopForm.getConnectionBox();

      if (box != null) {
        box.updateClientFields();
        box.updateConnectionFields();
        box.updateConnectionStatus();
      }

      MessageBox.showOkMessage(TEXTS.get("MQTTClient"), null, TEXTS.get("MqttConnectionLost"));
    }
  }

  private DesktopForm getDesktopForm() {
    if (Desktop.get() == null) {
      return null;
    }
    else {
      return Desktop.get().findForm(DesktopForm.class);
    }
  }

}
