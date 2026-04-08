package com.demo.jvm.basics.design.pattern.decorator;

/** Created by xialei on 2017/1/2. */
public class Audi implements AutoMobile {
  @Override
  public double getPrice() {
    return 1000;
  }

  @Override
  public String getDesc() {
    return "audi";
  }
}
