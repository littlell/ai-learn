package com.demo.jvm.basics.servlet;

import com.demo.jvm.basics.servlet.LoggerFactory.Logger;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/** Created by xialei on 16/5/24. */
@WebListener
public class AppListener
    implements ServletContextListener,
        ServletRequestListener,
        ServletRequestAttributeListener,
        HttpSessionListener {
  private Logger initLogger = LoggerFactory.getInitLogger();
  private Logger destroyLogger = LoggerFactory.getDestroyLogger();

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    initLogger.write("Servlet容器创建: " + new Date());
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    destroyLogger.write("Servlet容器关闭: " + new Date());
  }

  public void requestDestroyed(ServletRequestEvent servletRequestEvent) {}

  public void requestInitialized(ServletRequestEvent servletRequestEvent) {}

  public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {}

  public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {}

  public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {}

  public void sessionCreated(HttpSessionEvent httpSessionEvent) {}

  public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {}
}
