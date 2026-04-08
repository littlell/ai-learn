package com.demo.jvm.basics.function;

import java.time.LocalDate;

public class FluentInterface {
  public static void main(String[] args) {

    LocalDate today = LocalDate.now();

    LocalDate futureDate = today.plusYears(2);
    futureDate = futureDate.minusMonths(1);
    futureDate = futureDate.plusDays(3);

    System.out.println(today);
    System.out.println(futureDate);

    // 链式调用
    System.out.println(LocalDate.now().plusYears(2).minusMonths(1).plusDays(3));
  }
}
