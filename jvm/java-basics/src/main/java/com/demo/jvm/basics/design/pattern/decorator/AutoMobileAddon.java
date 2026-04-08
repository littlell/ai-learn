package com.demo.jvm.basics.design.pattern.decorator;

/** Created by xialei on 2017/1/2. */
public abstract class AutoMobileAddon implements AutoMobile {
  private final AutoMobile autoMobile;

  public AutoMobileAddon(AutoMobile autoMobile) {
    this.autoMobile = autoMobile;
  }

  @Override
  public String getDesc() {
    return autoMobile.getDesc();
  }

  @Override
  public double getPrice() {
    return autoMobile.getPrice();
  }
}
