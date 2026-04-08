package com.demo.jvm.spring.core.javaconfig.pure;

import com.demo.jvm.spring.core.javaconfig.pure.repository.HelloRepository;
import com.demo.jvm.spring.core.javaconfig.pure.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class ServiceConfig {
  @Bean
  @Autowired
  public HelloService helloService(HelloRepository helloRepository) {
    return new HelloService(helloRepository);
  }
}
