package com.demo.jvm.spring.core.bean.aware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
