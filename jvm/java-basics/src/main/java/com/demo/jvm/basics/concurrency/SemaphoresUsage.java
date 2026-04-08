package com.demo.jvm.basics.concurrency;

import java.util.concurrent.Semaphore;

public class SemaphoresUsage {

  /**
   * 信号灯.
   */
  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(2, false);

    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        while (true) {
          try {
            System.out.println(Thread.currentThread().getName() + "trying to acquire semaphore.");
            semaphore.acquire();
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName() + " doing work.");
          semaphore.release();
        }
      }).start();
    }
  }
}
