package com.demo.jvm.basics.reactor;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class RangeDemo {
  public static void main(String[] args) {
    Flux<Integer> intervalFlux = Flux.range(1, 5);

    StepVerifier.create(intervalFlux)
        .expectNext(1)
        .expectNext(2)
        .expectNext(3)
        .expectNext(4)
        .expectNext(5)
        .verifyComplete();
  }
}
