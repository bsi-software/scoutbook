/**
 *
 */
package org.eclipse.scout.widget.client.services.lookup;

import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.lookup.CodeLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ICodeLookupCallVisitor;
import org.eclipse.scout.widget.shared.services.code.EventTypeCodeType;

/**
 * @author mzi
 */
public class EventTypeLookupCall extends CodeLookupCall<Long> {

  private static final long serialVersionUID = 1L;

  class LookupVisitor implements ICodeLookupCallVisitor<Long> {

    @Override
    public boolean visit(CodeLookupCall<Long> call, ICode<Long> code, int treeLevel) {
      return code.isActive();
    }
  }

  public EventTypeLookupCall() {
    super(EventTypeCodeType.class);
    setFilter(new LookupVisitor());
  }
}
