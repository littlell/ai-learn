package com.demo.jvm.basics.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class FirstClassFunction {
  public static void main(String[] args) {
    Consumer<String> consumer;

    Function<String, String> toLowerFunction = t -> t.toLowerCase();

    consumer = e -> System.out.println(toLowerFunction.apply(e));

    // use lambda expression as first-class entities
    Stream.of("HELLO", "WORLD").forEach(consumer);
  }
}
