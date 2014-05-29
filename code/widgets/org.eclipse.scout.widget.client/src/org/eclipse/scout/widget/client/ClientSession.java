package org.eclipse.scout.widget.client;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipse.scout.widget.client.ui.desktop.Desktop;

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
    // we have no server -> we don't need a service tunnel
    // setServiceTunnel(new ClientHttpServiceTunnel(this, UriUtility.toUrl(getBundle().getBundleContext().getProperty("server.url"))));

    CODES.getAllCodeTypes(org.eclipse.scout.widget.shared.Activator.PLUGIN_ID);
    setDesktop(new Desktop());
  }

  @Override
  public void execStoreSession() throws ProcessingException {
  }
}
