package com.demo.jvm.basics.jvm.gc.strategy;

/**
 * 对象内存优先从Eden区分配, 当Eden区没有足够的空间进行分配时， 虚拟机将进行一次Minor GC -Xms20M -Xmx20M -Xmn10M
 * -XX:SurvivorRatio=8-Xlog:gc*
 */
public class AllocEdenFirst {
  private static final int _1MB = 1024 * 1024;

  public static void main(String[] args) throws InterruptedException {
    byte[] allocation1, allocation2, allocation3, allocation4;

    System.out.println("start allocate 1");
    allocation1 = new byte[2 * _1MB];
    System.out.println("start allocate 2");
    allocation2 = new byte[2 * _1MB];
    System.out.println("start allocate 3");
    allocation3 = new byte[2 * _1MB];
    System.out.println("start allocate 4");
    allocation4 = new byte[4 * _1MB];
  }
}
