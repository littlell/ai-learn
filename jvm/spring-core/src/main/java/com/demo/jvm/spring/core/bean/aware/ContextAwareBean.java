package com.demo.jvm.spring.core.bean.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

public class ContextAwareBean implements ApplicationContextAware {
  private ApplicationContext context;

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
  }

  public ApplicationContext getContext() {
    return context;
  }

  @PostConstruct
  public void init() {
    System.out.println("I have the context reference: " + context);
  }
}
