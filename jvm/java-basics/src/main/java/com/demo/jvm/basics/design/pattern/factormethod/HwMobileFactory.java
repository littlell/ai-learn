package com.demo.jvm.basics.design.pattern.factormethod;

public class HwMobileFactory implements MobileFactory {
  @Override
  public Mobile create() {
    return new HwMobile();
  }
}
