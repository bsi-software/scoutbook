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
public class AnswerOptionsLookupCall extends LocalLookupCall<Integer> {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<LookupRow<Integer>> execCreateLookupRows() throws ProcessingException {
    ArrayList<LookupRow<Integer>> rows = new ArrayList<>();
    rows.add(new LookupRow<Integer>(IMessageBox.YES_OPTION, "IMessageBox.YES_OPTION"));
    rows.add(new LookupRow<Integer>(IMessageBox.NO_OPTION, "IMessageBox.NO_OPTION"));
    rows.add(new LookupRow<Integer>(IMessageBox.CANCEL_OPTION, "IMessageBox.CANCEL_OPTION"));
    return rows;
  }
}
