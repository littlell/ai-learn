package com.demo.jvm.spring.core.tx.propagation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("tx/propagation/applicationContext.xml");

    UserClientService userService = context.getBean("userClientService", UserClientService.class);

    userService.propagationRequire();

//    userService.propagationRequireNew();
//
//    userService.propagationSupport();
//
//    userService.propagationMandatory();
//
//    userService.propagationNotSupported();
//
//    userService.propagationNotNever();
  }
}
