package com.demo.jvm.basics.concurrency;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableUsage {

  /**
   * Callable使用技巧.
   */
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(1);

    //Callable<Integer> callable = () -> 1;
    Callable<Integer> callable = () -> {
      throw new RuntimeException("自定义运行时异常");
    };

    Future<Integer> future = executorService.submit(callable);

    try {
      // future.cancel(true)后会造成future.get()抛出CancellationException.
      // future.cancel(true);
      System.out.println(future.get());
    } catch (ExecutionException e) {
      System.out.println("发生异常了.");
      System.out.println("异常原因: " + e.getCause().getMessage());
    }
    Thread.currentThread().join(2000);

    executorService.shutdown();

    SECONDS.sleep(5);

    FutureTask<Void> futureTask = new FutureTask<>(() -> {
      System.out.println("future task test.");
      return null;
    });

    futureTask.run();
  }
}
