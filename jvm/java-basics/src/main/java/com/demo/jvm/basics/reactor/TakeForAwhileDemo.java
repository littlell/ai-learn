package com.demo.jvm.basics.reactor;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TakeForAwhileDemo {
  public static void main(String[] args) {
    Flux<String> nationalParkFlux = Flux.just(
            "Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Acadia"
        )
        .delayElements(Duration.ofSeconds(1))
        .take(Duration.ofMillis(3500));

    StepVerifier.create(nationalParkFlux)
        .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
        .verifyComplete();
  }
}
