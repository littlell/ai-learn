package com.demo.jvm.basics.function;

import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * 有两个泛型变量，用于将T类型转换为R类型
 */
public class FunctionType {
  public static void main(String[] args) {

    Function<String, String> function = s -> s;

    BiFunction<String, String, String> biFunction = (s1, s2) -> s1 + s2;

    DoubleFunction<Double> doubleFunction = i -> 2 * i;

    DoubleToIntFunction doubleToIntFunction = i -> 1;

    DoubleToLongFunction doubleToLongFunction = i -> 1L;

    IntFunction<Integer> intFunction = i -> i;

    IntToDoubleFunction intToDoubleFunction = i -> 1.0;

    IntToLongFunction intToLongFunction = i -> 1L;

    ToDoubleBiFunction<Integer, Integer> toDoubleBiFunction = (a, b) -> 1.0;

    IntStream.range(1, 10).mapToDouble(intToDoubleFunction).forEach(System.out::println);
    DoubleStream.of(1.0, 2.0).mapToInt(doubleToIntFunction).forEach(System.out::println);
  }
}
