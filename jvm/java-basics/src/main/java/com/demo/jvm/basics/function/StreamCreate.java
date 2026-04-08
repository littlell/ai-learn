package com.demo.jvm.basics.function;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreate {
  public static void main(String[] args) {
    int[] numbers = {3, 6, 8, 8, 4, 6, 3, 3, 5, 6, 9, 4, 3, 6};

    // fixed width stream

    IntStream intStream1 = Arrays.stream(numbers);

    IntStream intStream2 = IntStream.range(1, 10);

    intStream1.forEach(e -> System.out.print(e + " "));

    intStream2.forEach(e -> System.out.print(e + " "));

    // fixed width stream

    System.out.println();

    // create an infinite stream

    Stream.iterate("Going", m -> m + "...").limit(5).forEach(System.out::println);

    IntStream.iterate(0, i -> i + 1).limit(10).filter(e -> e > 4).forEach(System.out::println);

    Supplier supplier = Math::random;

    Stream.generate(supplier).limit(5).forEach(System.out::println);

    // create an infinite stream
  }
}
