/**
 *
 */
package org.eclipse.scout.widget.client.old;

import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.pages.AbstractExtensiblePageWithNodes;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widget.client.old.ui.desktop.pages.PageWithADetailformTablePage;
import org.eclipse.scout.widget.client.old.ui.desktop.pages.PageWithNodesNodePage;
import org.eclipse.scout.widget.client.old.ui.desktop.pages.PageWithTableTablePage;

/**
 * @author mzi
 */
public class PagesNodePage extends AbstractExtensiblePageWithNodes {

  @Override
  protected String getConfiguredIconId() {
    return AbstractIcons.TreeNode;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Pages");
  }

  @Override
  protected void execCreateChildPages(List<IPage> pageList) throws ProcessingException {
    PageWithTableTablePage pageWithTableTablePage = new PageWithTableTablePage();
    pageList.add(pageWithTableTablePage);

    PageWithNodesNodePage pageWithNodesNodePage = new PageWithNodesNodePage();
    pageList.add(pageWithNodesNodePage);

    PageWithADetailformTablePage pageWithADetailformPage = new PageWithADetailformTablePage();
    pageList.add(pageWithADetailformPage);
  }
}
