package com.demo.jvm.basics.function;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CurryingExample {
  public static void main(String[] args) {
    BiFunction<String, String, String> biFunctionConcat = (a, b) -> a + b;

    System.out.println(biFunctionConcat.apply("Cat", "Dog"));

    Function<String, Function<String, String>> curryConcat;

    curryConcat = a -> b -> biFunctionConcat.apply(a, b);

    System.out.println(curryConcat.apply("Cat").apply("Dog"));

    Function<Double, Function<Double, Double>> curryAdd = a -> b -> a + b;

    System.out.println(curryAdd.apply(3.0).apply(4.0));
  }
}
