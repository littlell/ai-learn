package com.demo.jvm.basics.function;

import java.util.function.BiFunction;

public class HighOrderFunctionReturn {
  public static void main(String[] args) {
    System.out.println(calculatePayFunction(1).apply(10, 15.75f));
  }

  public static BiFunction<Integer, Float, Float> calculatePayFunction(int type) {
    switch (type) {
      case 1:
        return (hours, payRate) -> hours * payRate;
      case 2:
        return (hours, payRate) -> 40 * payRate;
      case 3:
        return (hours, payRate) -> 500f + 0.15f * payRate;
      default:
        return null;
    }
  }
}
