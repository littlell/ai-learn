package com.demo.jvm.basics.reactor;

import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class BufferDemo {
  public static void main(String[] args) {
    Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");

    Flux<List<String>> bufferedFlux = fruitFlux.buffer(3);

    StepVerifier.create(bufferedFlux)
        .expectNext(Arrays.asList("apple", "orange", "banana"))
        .expectNext(Arrays.asList("kiwi", "strawberry"))
        .verifyComplete();
  }
}
