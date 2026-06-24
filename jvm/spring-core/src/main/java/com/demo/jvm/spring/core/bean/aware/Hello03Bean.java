package com.demo.jvm.spring.core.bean.aware;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Hello03Bean {

  @PostConstruct
  public void init03() {
    System.out.println("Hello03Bean initializing....");
  }

  @PreDestroy
  public void destroy03() {
    System.out.println("Hello03Bean destroying....");
  }

}
