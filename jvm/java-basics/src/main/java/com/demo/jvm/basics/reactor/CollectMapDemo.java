package com.demo.jvm.basics.reactor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class CollectMapDemo {
  public static void main(String[] args) {

    Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");

    Mono<Map<Character, String>> fruitMapMono = fruitFlux.collectMap(a -> a.charAt(0));

    StepVerifier.create(fruitMapMono)
        .expectNextMatches(map -> {
          return map.size() == 5 &&
              map.get('A').equals("Apple") &&
              map.get('B').equals("Banana") &&
              map.get('G').equals("Grape") &&
              map.get('O').equals("Orange") &&
              map.get('S').equals("Strawberry");
        })
        .verifyComplete();
  }
}