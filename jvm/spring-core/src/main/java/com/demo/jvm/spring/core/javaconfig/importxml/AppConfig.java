package com.demo.jvm.spring.core.javaconfig.importxml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:applicationContext.xml")
public class AppConfig {
  @Bean(name = "helloBean")
  public HelloBean helloBean() {
    return new HelloBean();
  }
}
