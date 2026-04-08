package com.demo.jvm.spring.boot.rsocket;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class Boot08MainTest {

  @Autowired
  RSocketRequester tcp;

  @Test
  public void handleGreetingTest() {
  }


}