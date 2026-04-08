package com.demo.jvm.spring.boot.rsocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class GreetingController {

  private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

  /**
   * rsocket-cli --request -i hello --route=greeting  ws://127.0.0.1:8080/rsocket
   */
  @MessageMapping("greeting")
  public Mono<String> handleGreeting(Mono<String> greetingMono) {
    return greetingMono
        .doOnNext(greeting ->
            log.info("Received a greeting: {}", greeting))
        .map(greeting -> "Hello back to you!");
  }

  /**
   * rsocket-cli --request -i hello --route=greeting/littlell  ws://127.0.0.1:8080/rsocket
   */
  @MessageMapping("greeting/{name}")
  public Mono<String> handleGreetingName(
      @DestinationVariable("name") String name,
      Mono<String> greetingMono) {
    return greetingMono
        .doOnNext(greeting ->
            log.info("Received a greeting from {} : {}", name, greeting))
        .map(greeting -> "Hello back to you!");
  }
}
