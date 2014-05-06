/**
 *
 */
package org.eclipse.scout.widget.client.services.lookup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

/**
 * @author mzi
 */
public class LocaleLookupCall extends LocalLookupCall<Locale> {

  private static final long serialVersionUID = 1L;

  private void sort(Locale[] locales) {
    Comparator<Locale> localeComparator = new Comparator<Locale>() {
      @Override
      public int compare(Locale locale1, Locale locale2) {
        return locale1.toString().compareTo(locale2.toString());
      }
    };
    Arrays.sort(locales, localeComparator);
  }

  @Override
  protected List<LookupRow<Locale>> execCreateLookupRows() throws ProcessingException {
    ArrayList<LookupRow<Locale>> rows = new ArrayList<>();

    Locale[] locales = SimpleDateFormat.getAvailableLocales();
    sort(locales);

    for (Locale locale : locales) {
      rows.add(new LookupRow<Locale>(locale, locale.getDisplayName()));
    }

    return rows;
  }
}
