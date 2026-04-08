package com.demo.jvm.basics.algorithm.recurse;

public class Power {

  public static void main(String[] args) {
    int result = power(3, 4);

    int resultPlus = powerPlus(3, 4);

    System.out.println(result);

    System.out.println(resultPlus);
  }

  public static int power(int m, int n) {

    if (n == 0) {
      return 1;
    }

    return m * power(m, n - 1);
  }

  public static int powerPlus(int m, int n) {

    if (n == 0) {
      return 1;
    }

    if (n % 2 == 0) {
      return powerPlus(m, n / 2) * powerPlus(m, n / 2);
    } else {
      return m * powerPlus(m, n / 2) * powerPlus(m, n / 2);
    }
  }
}