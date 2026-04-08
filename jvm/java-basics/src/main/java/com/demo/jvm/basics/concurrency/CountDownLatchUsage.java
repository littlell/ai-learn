package com.demo.jvm.basics.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchUsage {

  /**
   * CountDownLatch简单使用.
   */
  public static void main(String[] args) throws InterruptedException {
    final CountDownLatch refereeCountDownLatch = new CountDownLatch(1);
    final CountDownLatch playersCountDownLatch = new CountDownLatch(10);

    new Thread(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("referee is ready.");
      refereeCountDownLatch.countDown();
    }).start();

    for (int i = 0; i < 10; i++) {
      int finalI = i;
      new Thread(() -> {
        try {
          System.out.println(
              Thread.currentThread().getName() + " start waiting for the referee to be ready.");
          refereeCountDownLatch.await();
          if (finalI == 5) {
            Thread.sleep(5000);
          }
          playersCountDownLatch.countDown();
          System.out
              .println(Thread.currentThread().getName() + " waiting others to finish their job.");
          playersCountDownLatch.await();
        } catch (InterruptedException e) {
          playersCountDownLatch.countDown();
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " start running.");
      }).start();
    }
  }
}
