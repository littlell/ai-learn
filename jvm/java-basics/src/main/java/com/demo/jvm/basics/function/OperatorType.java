package com.demo.jvm.basics.function;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class OperatorType {
  public static void main(String[] args) {

    BinaryOperator<String> binaryOperator = (a, b) -> "hello";

    DoubleBinaryOperator doubleBinaryOperator = (a, b) -> 1.0;

    DoubleUnaryOperator doubleUnaryOperator = a -> 1.0;

    IntBinaryOperator intBinaryOperator = (a, b) -> 1;

    IntUnaryOperator intUnaryOperator = a -> 1;

    LongBinaryOperator longBinaryOperator = (a, b) -> 1L;

    LongUnaryOperator longUnaryOperator = a -> 1L;

    UnaryOperator<String> unaryOperator = a -> "hello";

    IntStream.range(1, 10).map(intUnaryOperator).forEach(System.out::println);
  }
}
