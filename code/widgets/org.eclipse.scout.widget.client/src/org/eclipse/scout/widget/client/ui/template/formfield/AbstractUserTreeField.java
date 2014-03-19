/**
 *
 */
package org.eclipse.scout.widget.client.ui.template.formfield;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.tree.AbstractTreeNode;
import org.eclipse.scout.rt.client.ui.basic.tree.ITree;
import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNode;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.data.basic.FontSpec;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;
import org.eclipse.scout.widget.shared.ui.template.formfield.AbstractUserTreeFieldData;

/**
 * @author mzi
 */
@FormData(value = AbstractUserTreeFieldData.class, sdkCommand = FormData.SdkCommand.CREATE, defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractUserTreeField extends AbstractStringField {

  @Override
  protected String getConfiguredLabelFont() {
    return "ITALIC";
  }

  @Override
  protected boolean getConfiguredMultilineText() {
    return true;
  }

  protected List<Node> parseFieldValue() {
    Map<String, Node> parents = new HashMap<String, Node>();
    Node root = new Node();

    clearErrorStatus();

    for (String line : getValue().split("\n")) {
      line = line.trim();

      if (line.length() > 0 && !line.startsWith("#")) {
        Node node = parseLine(line);

        if (node.isInvalid()) {
          setErrorStatus(node.invalidMessage() + ". Line: '" + line + "'");
          break;
        }

        parents.put(node.getKey(), node);
        Node parent = parents.get(node.getParentKey());

        if (parent == null) {
          root.addChild(node);
        }
        else {
          parent.addChild(node);
        }
      }
    }

    return root.getChildren();
  }

  private Node parseLine(String line) {
    String[] t = line.split(";");
    Node node = new Node();

    if (t.length != 8) {
      node.setInvalid(true);
      node.setInvalidMessage("Wrong number of tokens. Expected: 8 found: " + t.length);
      return node;
    }

    // set key
    if (!StringUtility.isNullOrEmpty(t[0])) {
      node.setKey(t[0]);
    }
    else {
      node.setInvalid(true);
      node.setInvalidMessage("Key must not be empty");
      return node;
    }

    node.setParentKey(t[1]);
    node.setText(t[2]);
    node.setIconId(t[3]);
    node.setToolTip(t[4]);
    node.setFont(parseFont(t[5]));
    node.setEnabled(parseBoolean(t[6]));
    node.setActive(parseBoolean(t[7]));

    return node;
  }

  private FontSpec parseFont(String font) {
    if (StringUtility.isNullOrEmpty(font)) {
      return null;
    }

    FontSpec f = new FontSpec("Arial", 0, 12);

    if (UserAgentUtility.isSwtUi()) {
      f = new FontSpec("Arial", 0, 8);
    }

    if (font.equals("italic")) {
      f.getItalicCopy();
    }
    else if (font.equals("bold")) {
      f.getBoldCopy();
    }

    return f;
  }

  private boolean parseBoolean(String bool) {
    return !(StringUtility.isNullOrEmpty(bool) && bool.equals("false"));
  }

  protected void printNodes(List<Node> nodes, String ident) {
    for (Node node : nodes) {
      System.out.println(ident + node);
      printNodes(node.getChildren(), ident + "  ");
    }
  }

  protected void addNodesToTree(List<Node> nodes, ITree tree, ITreeNode root) {
    for (Node node : nodes) {
      ITreeNode treeNode = nodeToTreeNode(node);
      tree.addChildNode(root, treeNode);

      if (!node.isLeaf()) {
        addNodesToTree(node.getChildren(), tree, treeNode);
      }
    }
  }

  private ITreeNode nodeToTreeNode(final Node node) {
    AbstractTreeNode treeNode = new AbstractTreeNode() {
      @Override
      protected void execDecorateCell(Cell cell) {
        cell.setIconId(node.getIconId());
        cell.setText(node.getText());
        cell.setTooltipText(node.getToolTip());
      }
    };

    treeNode.setLeaf(node.isLeaf());

    return treeNode;
  }

  protected List<IMenu> nodesToMenus(List<Node> nodes) {
    ArrayList<IMenu> menus = new ArrayList<IMenu>();

    for (final Node node : nodes) {
      AbstractMenu menu = new AbstractMenu() {
      };

      menu.setText(node.getText());
      menu.setTooltipText(node.getToolTip());
      menu.setIconId(node.getIconId());
      menu.setChildActions(nodesToMenus(node.getChildren()));

      menus.add(menu);
    }

    return menus;
  }

  private LookupRow createLookupRow(String key, String parent, String text, String icon, String tooltip, String font, String enabled, String active) {
    LookupRow row = new LookupRow(key, text, icon);

    // parent
    if (!StringUtility.isNullOrEmpty(parent)) {
      row.setParentKey(parent);
    }
    // tool tip
    if (!StringUtility.isNullOrEmpty(tooltip)) {
      row.setTooltipText(tooltip);
    }
    // font
    if (!StringUtility.isNullOrEmpty(font)) {
      FontSpec f = new FontSpec("Arial", 0, 12);

      if (UserAgentUtility.isSwtUi()) {
        f = new FontSpec("Arial", 0, 8);
      }

      if (font.equals("italic")) {
        row.setFont(f.getItalicCopy());
      }
      else if (font.equals("bold")) {
        row.setFont(f.getBoldCopy());
      }
    }
    // enabled
    if (enabled.equals("false")) {
      row.setEnabled(false);
    }
    // active
    if (!StringUtility.isNullOrEmpty(active) && active.equals("false")) {
      row.setActive(false);
    }

    return row;
  }

  public class Node {
    private String m_key;
    private String m_parentKey;
    private Node m_parent;
    private List<Node> m_children;

    private String m_text;
    private String m_icon;
    private String m_toolTip;
    private FontSpec m_font;
    private boolean m_enabled;
    private boolean m_active;

    private boolean m_invalid;
    private String m_invalidMessage;

    public Node() {
      m_parent = null;
      m_children = new ArrayList<Node>();
    }

    @Override
    public String toString() {
      StringBuffer buf = new StringBuffer();
      buf.append(getKey() + "[" + getParentKey());
      buf.append("," + getText());
      buf.append("," + getIconId());
      buf.append("]");
      return buf.toString();
    }

    public void addChild(Node child) {
      child.m_parent = this;
      m_children.add(child);
    }

    public List<Node> getChildren() {
      return m_children;
    }

    public boolean isLeaf() {
      return m_children.size() == 0;
    }

    public boolean isRoot() {
      return m_parent == null;
    }

    String getText() {
      return m_text;
    }

    void setText(String text) {
      m_text = text;
    }

    String getToolTip() {
      return m_toolTip;
    }

    void setToolTip(String toolTip) {
      m_toolTip = toolTip;
    }

    FontSpec getFont() {
      return m_font;
    }

    void setFont(FontSpec font) {
      m_font = font;
    }

    boolean isEnabled() {
      return m_enabled;
    }

    void setEnabled(boolean enabled) {
      m_enabled = enabled;
    }

    boolean isActive() {
      return m_active;
    }

    void setActive(boolean active) {
      m_active = active;
    }

    void setInvalid(boolean invalid) {
      m_invalid = invalid;
    }

    boolean isInvalid() {
      return m_invalid;
    }

    void setInvalidMessage(String message) {
      m_invalidMessage = message;
    }

    String invalidMessage() {
      return m_invalidMessage;
    }

    private String getParentKey() {
      return m_parentKey;
    }

    private void setParentKey(String parentKey) {
      m_parentKey = parentKey;
    }

    private String getKey() {
      return m_key;
    }

    private void setKey(String key) {
      m_key = key;
    }

    private String getIconId() {
      return m_icon;
    }

    private void setIconId(String icon) {
      m_icon = icon;
    }
  }

}
