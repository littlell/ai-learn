package com.demo.jvm.basics.concurrency;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceUsage {

  /**
   * ExecutorCompletionService使用. 有执行完的线程任务就可以get到相应的返回值处理, 普通的线程池对象, 只有全部线程处理完, 才可以一次性处理各自的返回值.
   */
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    final List<Callable<Integer>> completeCallables = new ArrayList<Callable<Integer>>() {
      {
        add(() -> {
          Thread.sleep(5000);
          return 1;
        });
        add(() -> {
          Thread.sleep(3000);
          return 2;
        });
        add(() -> {
          Thread.sleep(2000);
          return 3;
        });
      }
    };
    final List<Callable<Integer>> normalCallables = new ArrayList<Callable<Integer>>() {
      {
        add(() -> {
          Thread.sleep(5000);
          return 1;
        });
        add(() -> {
          Thread.sleep(3000);
          return 2;
        });
        add(() -> {
          Thread.sleep(2000);
          return 3;
        });
      }
    };

    ExecutorService completeExecutorService = Executors.newCachedThreadPool();
    ExecutorCompletionService<Integer> executorCompletionService = new ExecutorCompletionService<>(
        completeExecutorService);

    completeCallables.forEach((e) -> executorCompletionService.submit(e));

    System.out.println(Calendar.getInstance().getTime() + " executorCompletionService start.");
    for (int i = 0; i < completeCallables.size(); i++) {
      Future<Integer> future = executorCompletionService.take();

      System.out.println(future.get() + " -> " + Calendar.getInstance().getTime());
    }
    System.out.println(Calendar.getInstance().getTime() + " executorCompletionService end.");

    completeExecutorService.shutdown();

    SECONDS.sleep(5);

    ExecutorService normalExecutorService = Executors.newCachedThreadPool();
    List<Future<Integer>> futures = new ArrayList<Future<Integer>>() {
      {
        normalCallables.forEach((e) -> add(normalExecutorService.submit(e)));
      }
    };

    System.out.println(Calendar.getInstance().getTime() + " normalExecutorService start.");
    for (Future<Integer> future : futures) {
      System.out.println(future.get() + " -> " + Calendar.getInstance().getTime());
      // 可为get方法设置超时, 超时将抛出异常.
      //System.out.println(future.get(3, TimeUnit.SECONDS)
      // + " -> " + Calendar.getInstance().getTime());
    }
    System.out.println(Calendar.getInstance().getTime() + " normalExecutorService end.");

    normalExecutorService.shutdown();
  }
}
