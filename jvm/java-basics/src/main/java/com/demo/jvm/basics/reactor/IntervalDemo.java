package com.demo.jvm.basics.reactor;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class IntervalDemo {
  public static void main(String[] args) {
    Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1))
        .take(5);

    StepVerifier.create(intervalFlux)
        .expectNext(0L)
        .expectNext(1L)
        .expectNext(2L)
        .expectNext(3L)
        .expectNext(4L)
        .verifyComplete();
  }
}
