package com.demo.jvm.basics.concurrency;

public class YieldUse implements Runnable {
  private String name;

  public YieldUse(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    for (int i = 0; i < 50; i++) {
      System.out.printf("%s-%d\n", name, i);
      if (i % 10 == 0) {
        Thread.yield();
      }
    }
  }

  /** Thread.yield 给别人一个机会， 一起抢一下 */
  public static void main(String[] args) {
    Runnable runnable1 = new YieldUse("t1");
    Runnable runnable2 = new YieldUse("t2");

    new Thread(runnable1).start();
    new Thread(runnable2).start();
  }
}
