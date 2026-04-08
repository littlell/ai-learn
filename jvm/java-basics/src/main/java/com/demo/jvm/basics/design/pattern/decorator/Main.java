package com.demo.jvm.basics.design.pattern.decorator;

public class Main {
  /** Created by xialei on 2017/1/2. */
  public static void main(String[] args) {
    AutoMobile autoMobile = new Audi();
    AutoMobile audiWithCcd  = new CDecorator(autoMobile);
    AutoMobile audiWithCcdAndSkyFloor = new SkyFloorDecorator(audiWithCcd);

    System.out.println(audiWithCcdAndSkyFloor.getPrice());
    System.out.println(audiWithCcdAndSkyFloor.getDesc());
  }
}
