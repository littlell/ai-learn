package com.demo.jvm.spring.core.ioc.instancefactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("ioc/instancefactory/applicationContext.xml");
    Hello01Bean hello01Bean = context.getBean("hello01Bean", Hello01Bean.class);
    Hello02Bean hello02Bean = context.getBean("hello02Bean", Hello02Bean.class);
    System.out.println(hello01Bean);
    System.out.println(hello02Bean);
  }
}
