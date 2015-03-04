/**
 *
 */
package org.eclipsescout.demo.widgets.client.services;

import java.awt.image.BufferedImage;
import java.util.List;

import org.eclipse.scout.rt.client.services.common.icon.IIconProviderService;

/**
 * @author mzi
 */
public interface IFontIconProviderService extends IIconProviderService {
  public BufferedImage getBufferedImageIcon(String iconName);

  public List<String> getIconKeys();

  public List<String> getFontKeys();
}
