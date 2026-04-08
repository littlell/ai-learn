package com.demo.jvm.spring.core.annotation.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("annotation/config/applicationContext.xml");

    OrderService orderService = context.getBean("orderService", OrderService.class);

    System.out.println(orderService);
  }
}
