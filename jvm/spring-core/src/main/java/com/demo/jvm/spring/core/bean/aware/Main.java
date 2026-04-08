package com.demo.jvm.spring.core.bean.aware;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean/aware/applicationContext.xml");

    BeanNameAwareBean beanNameAwareBean = context.getBean(BeanNameAwareBean.class);
    System.out.println(beanNameAwareBean.getBeanName());

    context.registerShutdownHook();
  }
}
