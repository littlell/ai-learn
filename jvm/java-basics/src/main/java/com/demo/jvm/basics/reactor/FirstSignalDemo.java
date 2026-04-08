package com.demo.jvm.basics.reactor;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FirstSignalDemo {
  public static void main(String[] args) {
    Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth")
        .delaySubscription(Duration.ofMillis(100));

    Flux<String> fastFlux = Flux.just("hare", "cheetah", "squirrel");

    Flux<String> firstFlux = Flux.firstWithSignal(slowFlux, fastFlux);

    StepVerifier.create(firstFlux)
        .expectNext("hare")
        .expectNext("cheetah")
        .expectNext("squirrel")
        .verifyComplete();
  }
}
