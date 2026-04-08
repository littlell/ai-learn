package com.demo.jvm.basics.concurrency;

public class VolatileNotAtom {

  public volatile int inc = 0;

  public void increase() {
    inc++;
  }

  /**
   * volatile变量能解决可见性问题， 不能解决原子性问题.
   */
  public static void main(String[] args) throws InterruptedException {
    final VolatileNotAtom volatileNotAtom = new VolatileNotAtom();

    for (int i = 0; i < 10; i++) {
      new Thread(
          () -> {
            for (int j = 0; j < 100; j++) {
              volatileNotAtom.increase();
            }
          })
          .start();
    }

    Thread.sleep(2000);

    System.out.println(volatileNotAtom.inc);
  }
}
