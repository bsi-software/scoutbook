/**
 *
 */
package org.eclipse.scout.transport.shared.services.lookup;

import java.util.Date;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

/**
 * @author mzi
 */
public class EventLookupCall extends LookupCall<String> {

  private static final long serialVersionUID = 1L;
  private Date m_fromDate;
  private Date m_toDate;

  @Override
  protected Class<? extends ILookupService<String>> getConfiguredService() {
    return IEventLookupService.class;
  }

  @FormData
  public Date getFromDate() {
    return m_fromDate;
  }

  @FormData
  public void setFromDate(Date fromDate) {
    m_fromDate = fromDate;
  }

  @FormData
  public Date getToDate() {
    return m_toDate;
  }

  @FormData
  public void setToDate(Date toDate) {
    m_toDate = toDate;
  }
}
