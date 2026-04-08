package com.demo.jvm.basics.jvm.classloader.passive;

public class ConstClass {

  static {
    System.out.println("ConstClass init!");
  }

  public static final String HELLOWORLD = "hello world";
}
