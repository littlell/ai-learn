package com.demo.jvm.spring.core.annotation.scanning;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("annotation/scanning/applicationContext.xml");

    HelloService helloService = context.getBean("helloService", HelloService.class);

    System.out.println(helloService);
  }
}
