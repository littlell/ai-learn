package com.demo.jvm.basics.reactor;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class SkipAFewDemo {
  public static void main(String[] args) {
    Flux<String> countFlux = Flux.just(
        "one", "two", "skip a few", "ninety nine", "one hundred"
    ).skip(3);

    StepVerifier.create(countFlux)
        .expectNext("ninety nine", "one hundred")
        .verifyComplete();
  }
}
