package com.demo.jvm.basics.reactor;

import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class AllDemo {
  public static void main(String[] args) {

    Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape");

    Mono<Boolean> hasEMono = fruitFlux.all(a -> a.contains("e"));
    StepVerifier.create(hasEMono)
        .expectNext(true)
        .verifyComplete();

    Mono<Boolean> hasAMono = fruitFlux.all(a -> a.contains("a"));
    StepVerifier.create(hasAMono)
        .expectNext(false)
        .verifyComplete();
  }
}