package com.demo.jvm.basics.design.pattern.factormethod;

public class HwMobile implements Mobile {
  @Override
  public void call() {
    System.out.println("you've got a call from HW");
  }
}
