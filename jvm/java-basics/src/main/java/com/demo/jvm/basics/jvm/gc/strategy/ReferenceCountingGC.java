package com.demo.jvm.basics.jvm.gc.strategy;

/**
 * 对象之间依旧存在引用， 但依然可以被gc回收， 说明java没有使用引用计数法来管理内存 -Xlog:gc*
 */
public class ReferenceCountingGC {

  public Object instance = null;

  /**
   * 这个成员的唯一意义就是占点内存， 以便能在GC日志中看清楚是否被回收过
   */
  private static final int _1MB = 1024 * 1024;

  private byte[] bigSize = new byte[2 * _1MB];

  public static void main(String[] args) {

    ReferenceCountingGC objA = new ReferenceCountingGC();
    ReferenceCountingGC objB = new ReferenceCountingGC();

    objA.instance = objB;
    objB.instance = objA;

    System.gc();

    objA = null;
    objB = null;

    System.gc();
  }
}
