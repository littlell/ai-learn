package com.demo.jvm.basics.design.pattern.singleton;

/** Created by xialei on 2017/1/6. */
public class EagerInstantiationSingleton {
  private static EagerInstantiationSingleton instance = new EagerInstantiationSingleton();

  private EagerInstantiationSingleton() {}

  public static EagerInstantiationSingleton getInstance() {
    return instance;
  }

  public static void main(String[] args) {
    System.out.println(EagerInstantiationSingleton.getInstance().hashCode());
    System.out.println(EagerInstantiationSingleton.getInstance().hashCode());
  }
}
