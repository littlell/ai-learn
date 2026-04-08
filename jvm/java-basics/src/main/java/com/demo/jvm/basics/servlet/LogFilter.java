package com.demo.jvm.basics.servlet;

import static com.demo.jvm.basics.servlet.LoggerFactory.Logger;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by xialei on 16/5/25.
 */
@WebFilter(
    filterName = "LogFilter",
    urlPatterns = {"/*"},
    asyncSupported = true,
    initParams = {
        @WebInitParam(name = "param1", value = "value1"),
        @WebInitParam(name = "param2", value = "value2")
    }
)
public class LogFilter implements Filter {

  private Logger logger = LoggerFactory.getNormalLogger();

  public void destroy() {
    logger.write("LogFilter destroyed.");
  }

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws ServletException, IOException {
    logger.write("LogFilter doFilter.");
    chain.doFilter(req, resp);
  }

  public void init(FilterConfig config) throws ServletException {
    logger.write("LogFilter init.");
    logger.write("LogFilter initParams: " + config.getInitParameterNames());
  }
}
