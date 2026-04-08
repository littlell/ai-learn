package com.demo.jvm.basics.jvm.gc.strategy;

/**
 * 大对象直接进入老年代 -Xlog:gc* -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 */
public class BigObjectDirectOld {

  private static final int _1MB = 1024 * 1024;

  public static void main(String[] args) {
    byte[] allocation;
    allocation = new byte[4 * _1MB];
  }
}
