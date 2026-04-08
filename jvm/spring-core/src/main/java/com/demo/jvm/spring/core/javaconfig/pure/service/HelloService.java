package com.demo.jvm.spring.core.javaconfig.pure.service;

import com.demo.jvm.spring.core.javaconfig.pure.repository.HelloRepository;

public class HelloService {
  private HelloRepository helloRepository;

  public HelloService(HelloRepository helloRepository) {
    this.helloRepository = helloRepository;
  }

  @Override
  public String toString() {
    return "HelloService{" +
        "helloRepository=" + helloRepository +
        '}';
  }
}
