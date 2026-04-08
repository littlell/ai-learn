package com.demo.jvm.spring.core.javaconfig.importjava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(name = "helloBean")
    public HelloBean helloBean() {
        return new HelloBean();
    }
}
