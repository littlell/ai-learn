package com.demo.jvm.spring.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/env")
public class EnvController {

  @Value("${foo}")
  private String foo;

  @GetMapping
  public String getEnvironment() {
    return "Hello, " + foo;
  }
}


