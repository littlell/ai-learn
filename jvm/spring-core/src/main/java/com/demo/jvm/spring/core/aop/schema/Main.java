package com.demo.jvm.spring.core.aop.schema;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("aop/schema/applicationContext.xml");

    FooService fooService = context.getBean("fooService", FooService.class);

    fooService.getFoo("foo", 15);

    System.out.println();

    try {
      fooService.getFoo("foo", -1);
    } catch (Exception e) {
      System.out.println("error happened.");
    }
  }
}
