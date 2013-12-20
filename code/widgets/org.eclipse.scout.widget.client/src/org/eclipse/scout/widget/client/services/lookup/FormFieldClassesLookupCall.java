/**
 * 
 */
package org.eclipse.scout.widget.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.doublefield.AbstractDoubleField;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

/**
 * @author mzi
 */
public class FormFieldClassesLookupCall extends LocalLookupCall {

  private static final long serialVersionUID = 1L;

  private void addNode(ArrayList<LookupRow> rows, Class node) {
    System.out.println(node.toString());

    if (node.toString().startsWith("class java.lang.Object")) {
      return;
    }

    for (LookupRow row : rows) {
      if (row.getKey() == node) {
        return;
      }
    }

    Class parent = node.getSuperclass();

    rows.add(new LookupRow(
        node, // key
        node.getSimpleName(), // text
        null, // iconId
        null, // toolTip
        null, // backgroundColor
        null, // foregroundColor
        null, // font
        true, // enabled
        parent));

    addNode(rows, parent);
  }

  @Override
  protected List<LookupRow> execCreateLookupRows() throws ProcessingException {
    ArrayList<LookupRow> rows = new ArrayList<LookupRow>();

    addNode(rows, AbstractLabelField.class);
    addNode(rows, AbstractStringField.class);
    addNode(rows, AbstractDateField.class);
    addNode(rows, AbstractDoubleField.class);
    addNode(rows, AbstractIntegerField.class);
    addNode(rows, AbstractSmartField.class);
    addNode(rows, AbstractHtmlField.class);

    return rows;
  }
}
