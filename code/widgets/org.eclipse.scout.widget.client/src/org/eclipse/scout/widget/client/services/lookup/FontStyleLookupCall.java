/**
 *
 */
package org.eclipse.scout.widget.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

/**
 * @author mzi
 */
public class FontStyleLookupCall extends LocalLookupCall<Integer> {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<LookupRow<Integer>> execCreateLookupRows() throws ProcessingException {
    ArrayList<LookupRow<Integer>> rows = new ArrayList<>();
    rows.add(new LookupRow<Integer>(0, TEXTS.get("Default")));
    rows.add(new LookupRow<Integer>(1, TEXTS.get("Bold")));
    rows.add(new LookupRow<Integer>(2, TEXTS.get("Italic")));
    rows.add(new LookupRow<Integer>(3, TEXTS.get("Bold") + " " + TEXTS.get("Italic")));
    return rows;
  }
}
