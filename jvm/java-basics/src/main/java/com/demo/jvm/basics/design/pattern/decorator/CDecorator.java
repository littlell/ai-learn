package com.demo.jvm.basics.design.pattern.decorator;

/** Created by xialei on 2017/1/2. */
public class CDecorator extends AutoMobileAddon {
  public CDecorator(AutoMobile autoMobile) {
    super(autoMobile);
  }

  @Override
  public double getPrice() {
    return 10 + super.getPrice();
  }

  @Override
  public String getDesc() {
    return super.getDesc() + ", " + "CCD";
  }
}
