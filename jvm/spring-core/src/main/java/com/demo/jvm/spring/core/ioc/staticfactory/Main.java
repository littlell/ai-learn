package com.demo.jvm.spring.core.ioc.staticfactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("ioc/staticfactory/applicationContext.xml");
    HelloBean helloBean = context.getBean("helloBean", HelloBean.class);
    System.out.println(helloBean);
  }
}
