package com.demo.jvm.spring.core.lifecycle.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException {
    ApplicationContext context = new ClassPathXmlApplicationContext("lifecycle/scope/applicationContext.xml");
    System.out.println(context.getBean("hello1"));
    System.out.println(context.getBean("hello1"));
    System.out.println(context.getBean("hello2"));
    System.out.println(context.getBean("hello2"));
  }
}
