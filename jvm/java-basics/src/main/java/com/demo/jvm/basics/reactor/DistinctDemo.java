package com.demo.jvm.basics.reactor;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class DistinctDemo {
  public static void main(String[] args) {
    Flux<String> nationalParkFlux = Flux.just(
            "dog", "cat", "bird", "dog", "bird", "anteater"
        )
        .distinct();

    StepVerifier.create(nationalParkFlux)
        .expectNext("dog", "cat", "bird", "anteater")
        .verifyComplete();
  }
}
