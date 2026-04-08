package com.demo.jvm.spring.core.di.nullempty;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException {
    ApplicationContext context = new ClassPathXmlApplicationContext("di/nullempty/applicationContext.xml");

    NullableBean nullableBean = context.getBean("nullableBean", NullableBean.class);
    System.out.println(nullableBean);
  }
}
