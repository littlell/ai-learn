package com.demo.jvm.spring.core.javaconfig.importjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("javaconfig/importjava/applicationContext.xml");

    UserBean userBean = context.getBean("userBean", UserBean.class);
    System.out.println(userBean);

    HelloBean helloBean = context.getBean("helloBean", HelloBean.class);
    System.out.println(helloBean);
  }
}
