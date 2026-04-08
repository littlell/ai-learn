package com.demo.jvm.basics.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Created by xialei on 16/5/30. */
@WebServlet(
    name = "AsyncServlet",
    urlPatterns = {"/asyncServlet"},
    asyncSupported = true
)
public class AsyncServlet extends HttpServlet {
  private LoggerFactory.Logger logger = LoggerFactory.getNormalLogger();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}

  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    final PrintWriter writer = response.getWriter();
    writer.print("<html>");
    writer.print("<head>");
    writer.print("</head>");
    writer.print("<body>");
    writer.print("<div id='main'>");
    writer.print("Async Page!");
    writer.print("</div>");
    writer.print("</body>");
    writer.print("</html>");

    final AsyncContext asyncContext = request.startAsync();
    asyncContext.setTimeout(100000);
    asyncContext.addListener(new MyAsyncListener());
    logger.write("Main Thread: " + Thread.currentThread().getName());
    asyncContext.start(
        new Runnable() {
          public void run() {
            try {
              logger.write("Worker Thread: " + Thread.currentThread().getName());
              for (int i = 99; i > 0; i--) {
                Thread.currentThread().sleep(1000);
                writer.print("<script>");
                writer.print(
                    "document.getElementById('main').innerHTML='" + (100 - i) + "%complete.'");

                writer.print("</script>");
                writer.flush();
              }
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            asyncContext.complete();
          }
        });
  }
}
