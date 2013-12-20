/**
 * 
 */
package org.eclipse.scout.widget.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

/**
 * @author mzi
 */
public class UserContentTreeLookupCall extends LocalLookupCall {

  private static final long serialVersionUID = 1L;
  private List<LookupRow> m_rows = new ArrayList<LookupRow>();

  public void setLookupRows(List<LookupRow> rows) {
    m_rows = rows;
  }

  @Override
  protected List<LookupRow> execCreateLookupRows() throws ProcessingException {
    return m_rows;
  }
}
