package com.demo.jvm.spring.core.javaconfig.pure;

import com.demo.jvm.spring.core.javaconfig.pure.bean.HelloBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@Import({RepositoryConfig.class, ServiceConfig.class})
public class AppConfig {

  @Bean(name = {"hello", "helloNew"})
  @Scope("prototype")
  public HelloBean helloBean() {
    return new HelloBean();
  }
}
