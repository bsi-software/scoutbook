/**
 *
 */
package org.eclipse.scout.transport.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.eclipse.scout.transport.shared.services.code.EventCodeType;

/**
 * @author mzi
 */
public class EventTypeLookupCall extends LocalLookupCall<Long> {

  private static final long serialVersionUID = 1L;
  private static final long OTHERS_ID = 10099L;

  @Override
  protected List<? extends ILookupRow<Long>> execCreateLookupRows() throws ProcessingException {
    ArrayList<LookupRow<Long>> rows = new ArrayList<>();

    // add active event codes as lookup rows
    for (ICode<Long> code : CODES.getCodeType(EventCodeType.class).getCodes(true)) {
      rows.add(new LookupRow<Long>(code.getId(), code.getText(), code.getIconId()));
    }

    // add lookup row to allow for transports not available in the code type
    rows.add(new LookupRow<Long>(OTHERS_ID, TEXTS.get("Any"), AbstractIcons.Empty));

    return rows;
  }
}
