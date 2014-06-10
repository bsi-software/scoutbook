/**
 *
 */
package org.eclipse.scout.transport.server.services.lookup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.scout.commons.DateUtility;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.server.services.lookup.AbstractLookupService;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.transport.shared.services.code.EventCodeType;
import org.eclipse.scout.transport.shared.services.entity.Event;
import org.eclipse.scout.transport.shared.services.lookup.EventLookupCall;
import org.eclipse.scout.transport.shared.services.lookup.IEventLookupService;

/**
 * @author mzi
 */
public class EventLookupService extends AbstractLookupService<String> implements IEventLookupService {

  private List<Event> m_list = null;

  private ICode<Long> code(Long codeId) {
    if (codeId == null) {
      return null;
    }

    return CODES.getCodeType(EventCodeType.class).getCode(codeId);
  }

  private List<Event> getEventList() {
    if (m_list == null) {
      m_list = new ArrayList<>();
      m_list.add(new Event("20140623", "Vesuvio", code(EventCodeType.BarCode.ID)));
      m_list.add(new Event("20141107", "Star Wars", code(EventCodeType.FilmCode.ID)));
      m_list.add(new Event("20150215", "Rome and Juliet", code(EventCodeType.DramaCode.ID)));
      m_list.add(new Event("20150428", "Harry's Bar", code(EventCodeType.BarCode.ID)));
    }

    return m_list;
  }

  private boolean matchesEventDates(Event event, ILookupCall<String> call) {
    EventLookupCall lc = (EventLookupCall) call;
    Date from = DateUtility.nvl(lc.getFromDate(), new Date(0));
    Date to = DateUtility.nvl(lc.getToDate(), new Date(Long.MAX_VALUE));
    return DateUtility.isInRange(from, event.getDate(), to);
  }

  private boolean matchesEventType(Event event, ILookupCall<String> call) {
    EventLookupCall lc = (EventLookupCall) call;
    ICode<Long> type = code(lc.getMasterAsLong());
    return (type == null || type.equals(event.getType()));
  }

  @Override
  public List<? extends ILookupRow<String>> getDataByAll(ILookupCall<String> call) throws ProcessingException {
    List<ILookupRow<String>> events = new ArrayList<>();

    for (Event event : getEventList()) {
      if (matchesEventDates(event, call) && matchesEventType(event, call)) {
        events.add(event.createLookupRow());
      }
    }

    return events;
  }

  @Override
  public List<? extends ILookupRow<String>> getDataByKey(ILookupCall<String> call) throws ProcessingException {
    List<ILookupRow<String>> events = new ArrayList<>();
    EventLookupCall lc = (EventLookupCall) call;

    System.out.println("getDataByKey, searching for " + lc.getKey());
    for (Event event : getEventList()) {
      System.out.println("checking event " + event + " (" + event.getId() + ")");
      if (event.getId().equals(lc.getKey())) {
        events.add(event.createLookupRow());
        System.out.println("found matching event " + event);
      }
    }

    return events;
  }

  @Override
  public List<? extends ILookupRow<String>> getDataByRec(ILookupCall<String> call) throws ProcessingException {
    return null;
  }

  @Override
  public List<? extends ILookupRow<String>> getDataByText(ILookupCall<String> call) throws ProcessingException {
    List<ILookupRow<String>> events = new ArrayList<>();
    EventLookupCall lc = (EventLookupCall) call;
    Pattern pattern = StringUtility.toRegEx(lc.getText(), Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    for (Event event : getEventList()) {
      if (event.getId() != null && pattern.matcher(event.toString().toLowerCase()).matches()) {
        events.add(event.createLookupRow());
      }
    }

    return events;
  }
}
