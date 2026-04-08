package com.demo.jvm.basics.design.pattern.singleton;

/** Created by xialei on 2017/1/6. */
public class SynchronizedSingleton {
  public static SynchronizedSingleton instance;

  private SynchronizedSingleton() {}

  /**
   * constunctor.
   * @return 单例对象
   */
  public static synchronized SynchronizedSingleton getInstance() {
    if (null == instance) {
      instance = new SynchronizedSingleton();
    }
    return instance;
  }

  public static void main(String[] args) {
    System.out.println(SynchronizedSingleton.getInstance().hashCode());
    System.out.println(SynchronizedSingleton.getInstance().hashCode());
  }
}
