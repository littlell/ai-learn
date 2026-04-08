package com.demo.jvm.basics.reactor;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TakeDemo {
  public static void main(String[] args) {
    Flux<String> nationalParkFlux = Flux.just(
            "Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Acadia"
        )
        .take(3);

    StepVerifier.create(nationalParkFlux)
        .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
        .verifyComplete();
  }
}
