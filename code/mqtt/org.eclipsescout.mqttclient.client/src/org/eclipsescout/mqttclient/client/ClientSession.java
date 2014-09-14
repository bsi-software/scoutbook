package org.eclipsescout.mqttclient.client;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipsescout.mqttclient.client.ui.desktop.Desktop;

public class ClientSession extends AbstractClientSession {
  private static IScoutLogger logger = ScoutLogManager.getLogger(ClientSession.class);

  public ClientSession() {
    super(true);
  }

  /**
   * @return session in current ThreadContext
   */
  public static ClientSession get() {
    return ClientJob.getCurrentSession(ClientSession.class);
  }

  @Override
  public void execLoadSession() throws ProcessingException {
    CODES.getAllCodeTypes(org.eclipsescout.mqttclient.shared.Activator.PLUGIN_ID);
    setDesktop(new Desktop());
  }

  @Override
  public void execStoreSession() throws ProcessingException {
  }
}
