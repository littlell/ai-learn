package com.demo.jvm.spring.boot.resilience4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExternalAPICaller {

  private final Logger LOGGER = LoggerFactory.getLogger(getClass());

  private boolean recovered = false;

  public void setRecovered(boolean recovered) {
    this.recovered = recovered;
  }

  public String callFailureApi() {
    if (recovered) {
      LOGGER.info("call health remote api.");
      return "hello";
    } else {
      LOGGER.info("call failure remote api.");
      throw new RuntimeException("ex");
    }
  }

  public String callSlownessApi() throws InterruptedException {
    if (recovered) {
      LOGGER.info("call fast remote api.");
    } else {
      LOGGER.info("call slowness remote api.");
      Thread.sleep(1500);
    }
    return "hello";
  }
}