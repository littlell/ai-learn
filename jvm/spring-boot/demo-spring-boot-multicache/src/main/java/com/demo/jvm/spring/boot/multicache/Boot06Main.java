package com.demo.jvm.spring.boot.multicache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Boot06Main {
  public static void main(String[] args) {
    SpringApplication.run(Boot06Main.class, args);
  }
}
