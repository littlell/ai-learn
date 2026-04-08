package com.demo.jvm.basics.reactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class CollectListDemo {
  public static void main(String[] args) {

    Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");

    Mono<List<String>> fruitListMono = fruitFlux.collectList();

    StepVerifier.create(fruitListMono)
        .expectNext(Arrays.asList("Apple", "Orange", "Grape", "Banana", "Strawberry"))
        .verifyComplete();
  }
}