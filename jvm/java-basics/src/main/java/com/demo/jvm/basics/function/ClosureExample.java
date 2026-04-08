package com.demo.jvm.basics.function;

import java.util.function.Function;

public class ClosureExample {
  int instanceLength = 1;

  public Function<String, String> getStringOperation() {
    String separator = ":";

    return target -> {
      int localLength = target.length();
      instanceLength = target.length();

      return target.toLowerCase() + separator + instanceLength + separator + localLength;
    };
  }

  public static void main(String[] args) {
    ClosureExample closureExample = new ClosureExample();
    Function<String, String> function = closureExample.getStringOperation();

    System.out.println(function.apply("Closure"));
  }

}
