package com.demo.jvm.basics.function;

import java.util.function.BiPredicate;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * 用于对某种类型的数据进行条件判断
 */
public class PredicateType {
  public static void main(String[] args) {

    Predicate<Integer> predicate = i -> i > 100;

    BiPredicate<Integer, Integer> biPredicate = (a, b) -> a > b;

    DoublePredicate doublePredicate = d -> d > 1.0;

    IntPredicate intPredicate = i -> i > 1;

    LongPredicate longPredicate = l -> l > 1L;

    System.out.println(predicate.test(101));

    System.out.println(biPredicate.test(1, 2));

    System.out.println(doublePredicate.test(2.0));

    System.out.println(intPredicate.test(2));

    System.out.println(longPredicate.test(0L));
  }
}
