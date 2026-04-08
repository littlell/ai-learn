package com.demo.jvm.basics.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池拒绝策略观察：有界队列 + AbortPolicy，提交超过容量时抛出 RejectedExecutionException.
 * 与 ThreadPoolUsage、SemaphoresUsage 并列，仅用于理解行为，不参与主业务.
 */
public class RejectedExecutionUsage {

  public static void main(String[] args) {
    int core = 2;
    int max = 2;
    int queueCap = 1;
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
        core, max,
        0L, TimeUnit.MILLISECONDS,
        new ArrayBlockingQueue<>(queueCap),
        new ThreadPoolExecutor.AbortPolicy()
    );

    for (int i = 1; i <= 5; i++) {
      final int id = i;
      try {
        executor.execute(() -> {
          System.out.println("任务 " + id + " 开始 " + Thread.currentThread().getName());
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          System.out.println("任务 " + id + " 结束");
        });
        System.out.println("提交任务 " + id + " 成功");
      } catch (RejectedExecutionException e) {
        System.out.println("提交任务 " + id + " 被拒绝: " + e.getMessage());
      }
    }

    executor.shutdown();
    try {
      executor.awaitTermination(15, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("主线程结束");
  }
}
