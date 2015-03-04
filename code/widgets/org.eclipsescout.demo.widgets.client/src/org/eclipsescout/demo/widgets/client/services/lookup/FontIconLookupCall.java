/**
 *
 */
package org.eclipsescout.demo.widgets.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.demo.widgets.client.services.IFontIconProviderService;

/**
 * @author mzi
 */
public class FontIconLookupCall extends LocalLookupCall<String> {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<? extends ILookupRow<String>> execCreateLookupRows() throws ProcessingException {
    List<LookupRow<String>> rows = new ArrayList<LookupRow<String>>();

    for (String key : SERVICES.getService(IFontIconProviderService.class).getIconKeys()) {
      rows.add(new LookupRow<String>(key, key, key + "!18"));
    }

    return rows;
  }
}
