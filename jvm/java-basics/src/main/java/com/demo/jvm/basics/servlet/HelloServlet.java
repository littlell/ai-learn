package com.demo.jvm.basics.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Created by xialei on 16/5/24. */
public class HelloServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();
    writer.print("<html>");
    writer.print("<head>");
    writer.print("</head>");
    writer.print("<body>");
    writer.print("Hello World!");
    writer.print("</body>");
    writer.print("</html>");
  }
}
