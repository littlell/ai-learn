package com.demo.jvm.basics.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class Main {

  public interface DemoLibrary extends Library {

    int sum(int a, int b);
  }

  public static void main(String[] args) {

    DemoLibrary demoLibrary = Native.load("DemoDll.dll", DemoLibrary.class);
    int result = demoLibrary.sum(2, 3);

    System.out.println(result);
  }
}
