package com.demo.jvm.basics.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * simpler and more flexible to replace method detail.
 */
public class HighOrderFunctionParameter {
  public static void main(String[] args) {

    // custom detail when used
    // lambda expression
    IntStream.range(1, 10).forEach(i -> System.out.println(processString(t -> Math.negateExact(t), i)));
    // method reference
    IntStream.range(1, 10).forEach(i -> System.out.println(processString(Math::incrementExact, i)));

    List<String> numberString = Arrays.asList("12", "34", "82");
    List<Integer> numbers = new ArrayList<>();

    Function<List<String>, List<Integer>> doubleStringList = e -> {
      e.stream().forEach(t -> numbers.add(2 * Integer.parseInt(t)));
      return numbers;
    };

    System.out.println(doubleStringList.apply(numberString));

    numbers.clear();

    // real power
    Arrays.asList(numberString).stream().map(doubleStringList).forEach(System.out::println);
  }

  // takes a function as parameter
  public static Integer processString(Function<Integer, Integer> operation, int target) {
    return operation.apply(target);
  }
}
