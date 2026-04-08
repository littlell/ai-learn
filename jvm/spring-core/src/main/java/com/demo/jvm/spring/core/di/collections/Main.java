package com.demo.jvm.spring.core.di.collections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException {
    ApplicationContext context = new ClassPathXmlApplicationContext("di/collections/applicationContext.xml");

    ComplexBean complexBean = context.getBean("complexBean", ComplexBean.class);
    System.out.println(complexBean);
  }
}
