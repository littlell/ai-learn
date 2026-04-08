package com.demo.jvm.basics.function;

import java.util.Arrays;
import java.util.List;

public class LambdaDebug {
  public static void main(String[] args) {

    List<String> list = Arrays.asList("Huey", "Dewey", "Louie");

    // use println
    list.stream().map(s -> {
      System.out.println("Before: " + s);
      s += "-" + s.toLowerCase();
      System.out.println("After: " + s);
      return s;
    }).forEach(s -> System.out.println(s));

    System.out.println();

    // use peek
    list.stream()
        .peek(s -> System.out.println("First peek-" + s))
        .map(s -> s + "-" + s.toLowerCase())
        .peek(s -> System.out.println(
            "Second peek-" + s + ":" + s.length()))
        .forEach(s -> System.out.println(s));
  }
}
