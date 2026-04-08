package com.demo.jvm.basics.design.pattern.singleton;

/** Created by xialei on 2017/1/6. 这里需要继续学习， 关于volatile关键字 */
public class DoubleCheckedLockingSingleton {
  private static volatile DoubleCheckedLockingSingleton instance;

  private DoubleCheckedLockingSingleton() {}

  /**
   * contructor.
   */
  public static DoubleCheckedLockingSingleton getInstance() {
    if (instance == null) {
      synchronized (DoubleCheckedLockingSingleton.class) {
        if (instance == null) {
          instance = new DoubleCheckedLockingSingleton();
        }
      }
    }

    return instance;
  }

  public static void main(String[] args) {
    System.out.println(DoubleCheckedLockingSingleton.getInstance().hashCode());
    System.out.println(DoubleCheckedLockingSingleton.getInstance().hashCode());
  }
}
