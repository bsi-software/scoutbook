package org.eclipse.scout.transport.shared.services.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.eclipse.scout.commons.DateUtility;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class Event implements Serializable {

  private static final long serialVersionUID = 1L;

  private String m_id = null;
  private String m_name = null;
  private Date m_date = null;
  private ICode<Long> m_type = null;

  public Event(String date, String name, ICode<Long> type) {
    m_id = UUID.randomUUID().toString();
    m_date = DateUtility.parse(date, "yyyyMMdd");
    m_name = name;
    m_type = type;
  }

  public Event(String id) {
    m_id = id;
    m_name = "<for search use only>";
  }

  public String getId() {
    return m_id;
  }

  public String getName() {
    return m_name;
  }

  public Date getDate() {
    return m_date;
  }

  public ICode<Long> getType() {
    return m_type;
  }

  @Override
  public String toString() {
    return getName() + " - " + DateUtility.format(getDate(), "dd.MM.yyyy");
  }

  public ILookupRow<String> createLookupRow() {
    return new LookupRow<String>(getId(), toString(), getType().getIconId());
  }
}
