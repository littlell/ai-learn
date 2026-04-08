package com.demo.jvm.basics.reactor;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FilterDemo {
  public static void main(String[] args) {
    Flux<String> nationalParkFlux = Flux.just(
            "Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Grand Teton"
        )
        .filter(np -> !np.contains(" "));

    StepVerifier.create(nationalParkFlux)
        .expectNext("Yellowstone", "Yosemite", "Zion")
        .verifyComplete();
  }
}
