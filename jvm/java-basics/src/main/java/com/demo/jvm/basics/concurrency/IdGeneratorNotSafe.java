package com.demo.jvm.basics.concurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IdGeneratorNotSafe {

  private static int count = 0;

  public static int nextId() {
    return count++;
  }

  /**
   * 存在并发问题的ID生成器 Created by xial-a on 2017/7/19.
   */
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    Callable<Integer> call1 =
        () -> {
          for (int i = 0; i < 100; i++) {
            System.out.println("Thread1: " + nextId());
          }
          return 0;
        };
    Callable<Integer> call2 =
        () -> {
          for (int i = 0; i < 100; i++) {
            System.out.println("Thread1: " + nextId());
          }
          return 0;
        };
    Callable<Integer> call3 =
        () -> {
          for (int i = 0; i < 100; i++) {
            System.out.println("Thread1: " + nextId());
          }
          return 0;
        };
    Callable<Integer> call4 =
        () -> {
          for (int i = 0; i < 100; i++) {
            System.out.println("Thread1: " + nextId());
          }
          return 0;
        };
    executorService.invokeAll(new ArrayList<Callable<Integer>>() {
      {
        add(call1);
        add(call2);
        add(call3);
        add(call4);
      }
    });

    executorService.shutdown();

    System.out.println("final count: " + count);
  }
}
