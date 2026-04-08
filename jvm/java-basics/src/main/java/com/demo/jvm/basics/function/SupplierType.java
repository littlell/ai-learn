package com.demo.jvm.basics.function;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

/**
 * 用于生产数据，生产数据类型通过泛型变量决定
 */
public class SupplierType {
  public static void main(String[] args) {
    Supplier<String> supplier = () -> "helloWorld";

    BooleanSupplier booleanSupplier = () -> true;

    DoubleSupplier doubleSupplier = () -> 1.0;

    IntSupplier intSupplier = () -> 1;

    LongSupplier longSupplier = () -> 1L;
  }
}
