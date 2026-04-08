package com.demo.jvm.spring.core.ioc.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("ioc/hello/applicationContext.xml");
    HelloBean helloBean = context.getBean("helloBean", HelloBean.class);
    System.out.println(helloBean);
  }
}
