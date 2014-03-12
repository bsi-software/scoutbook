/**
 *
 */
package org.eclipse.scout.widget.client.services.lookup;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

/**
 * @author mzi
 */
public class YearsMonthsLookupCall extends LocalLookupCall {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<LookupRow> execCreateLookupRows() throws ProcessingException {
    List<LookupRow> rows = new ArrayList<LookupRow>();

    int year = Calendar.getInstance().get(Calendar.YEAR);
    String[] months = new DateFormatSymbols().getMonths();

    for (int i = year; i < year + 5; i++) {
      String text = String.valueOf(i);
      String parentKey = String.valueOf(100 * i);

      rows.add(new LookupRow(parentKey, text));

      for (int j = 0; j < 12; j++) {
        String key = String.valueOf(100 * i + j + 1);
        LookupRow row = new LookupRow(key, months[j]);
        row.setParentKey(parentKey);
        rows.add(row);
      }
    }

    return rows;
  }
}
