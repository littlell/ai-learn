package com.demo.jvm.basics.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/** Created by xialei on 16/5/28. */
@WebFilter(
    filterName = "AutoCorrectFilter",
    urlPatterns = {"/*"},
    asyncSupported = true
)
public class AutoCorrectFilter implements Filter {
  public void destroy() {}

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest requestWrapper = new AutoCorrectWrapper((HttpServletRequest) req);
    chain.doFilter(requestWrapper, resp);
  }

  public void init(FilterConfig config) throws ServletException {}
}
