package com.demo.jvm.spring.boot.resilience4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/")
public class APIController {

  @Autowired
  ExternalAPICaller externalAPICaller;

  @GetMapping("/circuit-breaker/failure")
  @CircuitBreaker(name = "CircuitBreakerService")
  public String failure() {
    return externalAPICaller.callFailureApi();
  }

  @GetMapping("/circuit-breaker/slowness")
  @CircuitBreaker(name = "CircuitBreakerService")
  public String slowness() throws InterruptedException {
    return externalAPICaller.callSlownessApi();
  }
}