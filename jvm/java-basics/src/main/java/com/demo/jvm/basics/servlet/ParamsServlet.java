package com.demo.jvm.basics.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Created by xialei on 16/5/24. */
public class ParamsServlet extends HttpServlet {
  private List<String> londonAttractions;
  private List<String> parisAttractions;

  @Override
  public void init() throws ServletException {
    londonAttractions = new ArrayList<String>(10);
    londonAttractions.add("Buckingham Palace");
    londonAttractions.add("London Eye");
    londonAttractions.add("British Museum");
    londonAttractions.add("National Gallery");
    londonAttractions.add("Big Ben");
    londonAttractions.add("Tower of London");
    londonAttractions.add("Natural History Museum");
    londonAttractions.add("Canary Wharf");
    londonAttractions.add("2012 Olympic Park");
    londonAttractions.add("St Paul's Cathedral");
    parisAttractions = new ArrayList<String>(10);
    parisAttractions.add("Eiffel Tower");
    parisAttractions.add("Notre Dame");
    parisAttractions.add("The Louvre");
    parisAttractions.add("Champs Elysees");
    parisAttractions.add("Arc de Triomphe");
    parisAttractions.add("Sainte Chapelle Church");
    parisAttractions.add("Les Invalides");
    parisAttractions.add("Musee d'Orsay");
    parisAttractions.add("Montmarte");
    parisAttractions.add("Sacre Couer Basilica");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();
    writer.print(req.getParameter("city"));
  }
}
