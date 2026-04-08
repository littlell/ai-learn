package com.demo.jvm.basics.function;

import java.util.function.Function;

/**
 * @author xial-a
 */
public class FunctionComposition {
  public static void main(String[] args) {

    Function<Integer, Integer> baseFunction = t -> t + 2;

    // 原函数最后调用
    Function<Integer, Integer> afterFunction = baseFunction.andThen(t -> t * 3);

    // 原函数优先调用
    Function<Integer, Integer> beforeFunction = baseFunction.compose(t -> t * 3);

    System.out.println(afterFunction.apply(2));

    System.out.println(beforeFunction.apply(2));
  }
}
