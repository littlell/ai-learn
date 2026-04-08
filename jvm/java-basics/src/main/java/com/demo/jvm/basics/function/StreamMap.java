package com.demo.jvm.basics.function;

import java.util.stream.IntStream;

public class StreamMap {
  public static void main(String[] args) {

    // map
    IntStream.range(1, 4).map(e -> e * 2).forEach((e) -> {
      System.out.print(e + " ");
    });

    System.out.println();

    // reduce
    System.out.println(IntStream.range(1, 4).map(e -> e * 2).sum());

    System.out.println(IntStream.range(1, 4).map(e -> e * 2).reduce((a, b) -> a + b));

    System.out.println(IntStream.range(1, 4).map(e -> e * 2).reduce(0, (a, b) -> a + b));

    // a variable holds the cumulative sum, while the b variable holds the current stream item.
    System.out.println(IntStream.range(1, 4).map(e -> e * 2).reduce(0, (a, b) -> {
      System.out.println(a + "-" + b);
      return a + b;
    }));
  }
}
