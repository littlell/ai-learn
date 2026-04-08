package com.demo.jvm.spring.core.bean.aware;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Hello01Bean implements InitializingBean, DisposableBean {

  public void afterPropertiesSet() throws Exception {
    System.out.println("Hello01Bean initializing....");
  }

  public void destroy() {
    System.out.println("Hello01Bean destroying....");
  }
}
