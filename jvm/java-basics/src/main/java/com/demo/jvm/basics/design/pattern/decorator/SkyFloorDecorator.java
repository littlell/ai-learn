package com.demo.jvm.basics.design.pattern.decorator;

/** Created by xialei on 2017/1/2. */
public class SkyFloorDecorator extends AutoMobileAddon {
  public SkyFloorDecorator(AutoMobile autoMobile) {
    super(autoMobile);
  }

  @Override
  public double getPrice() {
    return 20 + super.getPrice();
  }

  @Override
  public String getDesc() {
    return super.getDesc() + ", " + "SkyFloor";
  }
}
