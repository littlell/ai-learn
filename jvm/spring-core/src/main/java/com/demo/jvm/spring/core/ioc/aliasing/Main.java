package com.demo.jvm.spring.core.ioc.aliasing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("ioc/aliasing/applicationContext.xml");
    HelloBean helloBean = context.getBean("helloBean", HelloBean.class);
    HelloBean helloRenameBean = context.getBean("helloRenameBean", HelloBean.class);
    System.out.println(helloBean);
    System.out.println(helloRenameBean);
  }
}
