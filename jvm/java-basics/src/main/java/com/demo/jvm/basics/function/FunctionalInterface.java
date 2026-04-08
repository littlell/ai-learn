package com.demo.jvm.basics.function;

import java.util.Arrays;

public class FunctionalInterface {

  public static void main(String[] args) {

    new Thread(() -> System.out.println("Thread")).start();

    Arrays.asList("foo", "bar").forEach(System.out::println);

    System.out.println(computeMethod(base -> (base + 1), 1));
  }

  interface Computable {
    int compute(int base);
  }

  private static int computeMethod(Computable computable, int src) {
    return computable.compute(src);
  }

}
