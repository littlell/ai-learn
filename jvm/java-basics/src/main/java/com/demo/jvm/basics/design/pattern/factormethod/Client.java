package com.demo.jvm.basics.design.pattern.factormethod;

public class Client {
  public static void main(String[] args) {
    MobileFactory factory = new HwMobileFactory();
    factory.create().call();

    factory = new XiaoMiMobileFactory();
    factory.create().call();
  }
}
