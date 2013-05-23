package org.eclipse.scout.widgets.server.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.widgets.shared.services.ISourceUrlService;

public class SourceUrlService extends AbstractService implements ISourceUrlService {

  private String m_baseUrl = null;

  public String getBaseUrl() {
    return m_baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    m_baseUrl = baseUrl;
  }

  @Override
  public String getSourceUrl(String clazz) throws ProcessingException {
    System.out.println("class name: " + clazz);
    System.out.println("base url: " + getBaseUrl());

    return getBaseUrl() + clazz.replace(".", "/") + ".java";
  }
}
