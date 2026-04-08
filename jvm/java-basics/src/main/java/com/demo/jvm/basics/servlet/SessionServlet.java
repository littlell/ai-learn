package com.demo.jvm.basics.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xialei on 16/5/24.
 */
@WebServlet(
    name = "SessionServlet",
    urlPatterns = {"/session"}
)
public class SessionServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (request.getSession().getAttribute("cart") == null) {
      request.getSession().setAttribute("cart", "my cart");
    }
    PrintWriter writer = response.getWriter();
    writer.print("<html>");
    writer.print("<head>");
    writer.print("</head>");
    writer.print("<body>");
    writer.print("sessions: " + request.getSession() + "</br>");
    writer.print("session[cart]: " + request.getSession().getAttribute("cart") + "</br>");
    writer.print("getMaxInactiveInterval: " + request.getSession().getMaxInactiveInterval());
    writer.print("</body>");
    writer.print("</html>");
  }
}
