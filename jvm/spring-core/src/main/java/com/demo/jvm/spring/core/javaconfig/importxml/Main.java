package com.demo.jvm.spring.core.javaconfig.importxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    UserBean userBean = context.getBean("userBean", UserBean.class);
    System.out.println(userBean);

    HelloBean helloBean = context.getBean("helloBean", HelloBean.class);
    System.out.println(helloBean);
  }
}
