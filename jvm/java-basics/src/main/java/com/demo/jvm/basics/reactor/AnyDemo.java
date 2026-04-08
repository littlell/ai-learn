package com.demo.jvm.basics.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class AnyDemo {
  public static void main(String[] args) {

    Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape");

    Mono<Boolean> hasOMono = fruitFlux.any(a -> a.contains("O"));
    StepVerifier.create(hasOMono)
        .expectNext(true)
        .verifyComplete();

    Mono<Boolean> hasYMono = fruitFlux.any(a -> a.contains("Y"));
    StepVerifier.create(hasYMono)
        .expectNext(false)
        .verifyComplete();
  }
}