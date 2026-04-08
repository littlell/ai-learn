package com.demo.jvm.basics.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierUsage {

  /**
   * 栅栏应用, 等待所有线程全部到达才全部继续执行.
   */
  public static void main(String[] args) {
    int threadCount = 4;
    CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount,
        () -> System.out.println("barrier meets."));

    for (int i = 0; i < threadCount; i++) {
      int finalI = i;
      new Thread(() -> {
        while (true) {
          try {
            Thread.sleep(2000);
            if (finalI == 3) {
              Thread.sleep(2000);
            }
            System.out.println(
                Thread.currentThread().getName() + " waiting others for reaching the barrier.");
            // 需要等待线程的个数
            // System.out.println(cyclicBarrier.getParties());
            // 正在等待线程的个数
            // System.out.println(cyclicBarrier.getNumberWaiting());
            cyclicBarrier.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          } catch (BrokenBarrierException e) {
            e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName() + " do it's job.");

          cyclicBarrier.reset();
        }
      }).start();
    }
  }
}
