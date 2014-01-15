/**
 *
 */
package org.eclipse.scout.widget.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.messagebox.IMessageBox;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

/**
 * @author mzi
 */
public class AnswerOptionsLookupCall extends LocalLookupCall {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<LookupRow> execCreateLookupRows() throws ProcessingException {
    ArrayList<LookupRow> rows = new ArrayList<LookupRow>();
    rows.add(new LookupRow(IMessageBox.YES_OPTION, "IMessageBox.YES_OPTION"));
    rows.add(new LookupRow(IMessageBox.NO_OPTION, "IMessageBox.NO_OPTION"));
    rows.add(new LookupRow(IMessageBox.CANCEL_OPTION, "IMessageBox.CANCEL_OPTION"));
    return rows;
  }
}
