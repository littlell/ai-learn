package com.demo.jvm.spring.core.javaconfig.pure;

import com.demo.jvm.spring.core.javaconfig.pure.bean.HelloBean;
import com.demo.jvm.spring.core.javaconfig.pure.service.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    ctx.scan("com.demo.jvm.spring.core.javaconfig.pure");

    HelloBean helloBean = ctx.getBean(HelloBean.class);
    System.out.println(helloBean);

    helloBean = ctx.getBean(HelloBean.class);
    System.out.println(helloBean);

    helloBean = (HelloBean) ctx.getBean("hello");
    System.out.println(helloBean);

    helloBean = (HelloBean) ctx.getBean("helloNew");
    System.out.println(helloBean);

    HelloService helloService = ctx.getBean(HelloService.class);
    System.out.println(helloService);
  }
}
