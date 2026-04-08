package com.demo.jvm.basics.jvm.classloader;

/**
 * 虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确地加锁和同步，
 * <p>
 * 如果多个线程同时去初始化一个类， 那么只会有一个线程去执行这个类的<clinit>()方法, 其他线程都需要阻塞等待，
 * <p>
 * 直到活动线程执行<clinit>()方法完毕。
 */
public class DeadLoopClass {

  static {
    if (true) {
      System.out.println(Thread.currentThread() + "init DeadLoopClass");
      while (true) {

      }
    }
  }

  public static void main(String[] args) {
    Runnable script = () -> {
      System.out.println(Thread.currentThread() + "start");
      DeadLoopClass deadLoopClass = new DeadLoopClass();
      System.out.println(Thread.currentThread() + "run over");
    };

    new Thread(script).run();
    new Thread(script).run();
  }

}
