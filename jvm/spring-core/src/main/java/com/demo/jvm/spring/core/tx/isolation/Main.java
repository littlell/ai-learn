package com.demo.jvm.spring.core.tx.isolation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("tx/isolation/applicationContext.xml");

    UserClientService userService = (UserClientService) context.getBean("userClientService");

//    userService.insert();

    System.out.println(userService.selectAgeByName("foo"));
  }
}
