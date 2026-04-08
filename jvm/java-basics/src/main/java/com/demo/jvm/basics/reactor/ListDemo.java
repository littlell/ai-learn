package com.demo.jvm.basics.reactor;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ListDemo {
  public static void main(String[] args) {

    List<String> fruitList = new ArrayList<>();
    fruitList.add("Apple");
    fruitList.add("Orange");
    fruitList.add("Grape");
    fruitList.add("Banana");
    fruitList.add("Strawberry");

    Flux<String> fruitFlux = Flux.fromIterable(fruitList);

    StepVerifier.create(fruitFlux)
        .expectNext("Apple")
        .expectNext("Orange")
        .expectNext("Grape")
        .expectNext("Banana")
        .expectNext("Strawberry")
        .verifyComplete();
  }
}