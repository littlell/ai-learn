package com.demo.jvm.spring.core.lifecycle.lazy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException {
    ApplicationContext context = new ClassPathXmlApplicationContext("lifecycle/lazy/applicationContext.xml");
    System.out.println(context.getBean("lazyBean"));
  }
}
