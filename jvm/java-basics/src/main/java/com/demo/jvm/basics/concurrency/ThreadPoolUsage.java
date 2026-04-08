package com.demo.jvm.basics.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUsage {

  /**
   * 线程池使用.
   */
  public static void main(String[] args) throws InterruptedException {
    // newFixedThreadPool的参数指定了可以运行的线程的最大数目，超过这个数目的线程加进去以后，不会运行.
    ExecutorService fixedSizeExecutorService = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 5; i++) {
      fixedSizeExecutorService.submit(() -> {
        while (true) {
          try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " hello newFixedThreadPool");
          } catch (InterruptedException e) {
            System.out.println("InterruptedException happened.");
            break;
          }
        }
        return;
      });
    }
    Thread.currentThread().join(5000);
    // 强行关闭线程池对象, 会对idle状态的线程发出中断状态.
    fixedSizeExecutorService.shutdownNow();

    Thread.sleep(2000);

    // newCachedThreadPool在线程够用的情况下会复用当前可用的线程, 若不够用则继续新开线程, 并且不设置线程数的上限.
    ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 5; i++) {
      cachedExecutorService.submit(() -> {
        while (true) {
          try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " hello newCachedThreadPool");
          } catch (InterruptedException e) {
            System.out.println("InterruptedException happened.");
            break;
          }
        }
        return;
      });
    }
    Thread.currentThread().join(5000);
    cachedExecutorService.shutdownNow();

    Thread.sleep(2000);

    // 单线程执行.
    ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 5; i++) {
      singleExecutorService.submit(() -> {
        while (true) {
          try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " hello singleExecutorService");
          } catch (InterruptedException e) {
            System.out.println("InterruptedException happened.");
            break;
          }
        }
        return;
      });
    }

    Thread.currentThread().join(5000);
    singleExecutorService.shutdownNow();

    Thread.sleep(2000);

    // 周期性的执行线程, 可配置延时启动以及运行频率.
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    for (int i = 0; i < 1; i++) {
      scheduledExecutorService.scheduleAtFixedRate(() -> {
        System.out.println(Thread.currentThread().getName() + " hello scheduledExecutorService");
      }, 5, 1, TimeUnit.SECONDS);
    }

  }
}
