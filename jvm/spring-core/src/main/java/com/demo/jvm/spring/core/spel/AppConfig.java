package com.demo.jvm.spring.core.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public User user(@Value("#{15}") int age, @Value("#{15 < 20? 'young' : 'old'}") String desc) {
    User user = new User();
    user.setAge(age);
    user.setDesc(desc);
    return user;
  }
}
