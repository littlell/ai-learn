package com.demo.jvm.spring.core.ioc.staticfactory;

public class HelloBean {
  private static HelloBean helloBean = new HelloBean();

  public static HelloBean createInstance() {
    return helloBean;
  }
}
