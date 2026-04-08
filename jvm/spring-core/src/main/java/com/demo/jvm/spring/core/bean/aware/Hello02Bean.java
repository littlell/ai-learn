package com.demo.jvm.spring.core.bean.aware;

public class Hello02Bean {
  public void init() {
    System.out.println("Hello02Bean initializing....");
  }

  public void destroy() {
    System.out.println("Hello02Bean destroying....");
  }
}
