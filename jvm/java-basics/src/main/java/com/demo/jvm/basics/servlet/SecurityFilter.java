package com.demo.jvm.basics.servlet;

import com.demo.jvm.basics.servlet.LoggerFactory.Logger;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/** Created by xialei on 16/5/25. */
public class SecurityFilter implements Filter {
  private Logger logger = LoggerFactory.getNormalLogger();

  public void destroy() {
    logger.write("SecurityFilter destroyed.");
  }

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws ServletException, IOException {
    logger.write("SecurityFilter doFilter.");
    chain.doFilter(req, resp);
  }

  public void init(FilterConfig config) throws ServletException {
    logger.write("SecurityFilter init.");
    logger.write("SecurityFilter initParams: " + config.getInitParameterNames());
  }
}
