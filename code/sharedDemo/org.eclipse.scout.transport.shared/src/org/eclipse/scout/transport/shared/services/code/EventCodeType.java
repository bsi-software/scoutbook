/**
 *
 */
package org.eclipse.scout.transport.shared.services.code;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;
import org.eclipse.scout.transport.shared.Icons;

/**
 * @author mzi
 */
public class EventCodeType extends AbstractCodeType<Long, Long> {

  private static final long serialVersionUID = 1L;
  public static final Long ID = 20000L;

  public EventCodeType() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("Event");
  }

  @Override
  public Long getId() {
    return ID;
  }

  @Order(10.0)
  public static class BarCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;
    public static final Long ID = 20010L;

    @Override
    protected String getConfiguredIconId() {
      return Icons.Bar;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Bar");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(20.0)
  public static class DramaCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;
    public static final Long ID = 20020L;

    @Override
    protected String getConfiguredIconId() {
      return Icons.Drama;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Drama");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(30.0)
  public static class FilmCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;
    public static final Long ID = 20030L;

    @Override
    protected String getConfiguredIconId() {
      return Icons.Film;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Film");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(40.0)
  public static class CatCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;
    public static final Long ID = 20040L;

    @Override
    protected String getConfiguredIconId() {
      return Icons.Cat;
    }

    @Override
    protected boolean getConfiguredActive() {
      return false;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Cat");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }
}
