package com.demo.jvm.basics.function;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamFilter {
  public static void main(String[] args) {

    String[] animals = {"cats", "dog", "ox", "bats", "horses", "mule"};

    Stream<String> animalStream = Arrays.stream(animals);

    // filter
    System.out.println("Filter result:");
    animalStream.filter(e -> e.matches(".*s$")).forEach(System.out::println);

    int[] numbers = {3, 3, 8};

    // distinct
    System.out.println("Distinct result:");
    Arrays.stream(numbers).distinct().forEach(System.out::println);

    // skip 2 head elements
    System.out.println("Skip result:");
    Arrays.stream(numbers).skip(2).forEach(System.out::println);
  }
}
