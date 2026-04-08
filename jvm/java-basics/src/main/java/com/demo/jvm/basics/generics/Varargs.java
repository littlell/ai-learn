package com.demo.jvm.basics.generics;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 泛型和可变参数结合使用时可能存在问题
 */
public class Varargs {

  public static void main(String[] args) {
    // 此处发生隐式转换, 将object[]转换为String[], 由于object[]不是String[]的子类型, 所以转换失败
    String[] attributes = pickTwo("Good", "Fast", "Cheap");

    // 类型安全
    List<String> attributesSafe = pickTwoSafe("Good", "Fast", "Cheap");
  }

  static <T> T[] toArray(T... args) {
    // 可变参数在方法内部用 object[] 存放
    return args;
  }

  static <T> T[] pickTwo(T a, T b, T c) {
    switch (ThreadLocalRandom.current().nextInt(3)) {
      case 0:
        // toArray返回的是object[]
        return toArray(a, b);
      case 1:
        return toArray(a, c);
      case 2:
        return toArray(b, c);
    }
    throw new AssertionError();
  }

  static <T> List<T> pickTwoSafe(T a, T b, T c) {
    switch (ThreadLocalRandom.current().nextInt(3)) {
      case 0:
        // toArray返回的是object[]
        return List.of(a, b);
      case 1:
        return List.of(a, c);
      case 2:
        return List.of(b, c);
    }
    throw new AssertionError();
  }
}
