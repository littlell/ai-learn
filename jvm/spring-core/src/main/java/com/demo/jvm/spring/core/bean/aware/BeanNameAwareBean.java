package com.demo.jvm.spring.core.bean.aware;

import org.springframework.beans.factory.BeanNameAware;

public class BeanNameAwareBean implements BeanNameAware {
  private String beanName;

  public void setBeanName(String s) {
    this.beanName = s;
  }

  public String getBeanName() {
    return beanName;
  }
}
