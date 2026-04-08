package com.demo.jvm.basics.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGeneratorWithAtom {

  private final AtomicInteger count = new AtomicInteger(0);

  public void nextId() {
    count.incrementAndGet();
  }

  public int getCount() {
    return count.intValue();
  }

  /**
   * 通过Atom原子类型保证线程安全
   * Created by xial-a on 2017/7/19.
   */
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    IdGeneratorWithAtom idGeneratorWithAtom = new IdGeneratorWithAtom();

    List<Callable<Integer>> tasks = new ArrayList<>();

    tasks.add(
        () -> {
          for (int i = 0; i < 100000; i++) {
            idGeneratorWithAtom.nextId();
          }
          return 0;
        });

    tasks.add(
        () -> {
          for (int i = 0; i < 100000; i++) {
            idGeneratorWithAtom.nextId();
          }
          return 0;
        });

    executorService.invokeAll(tasks);

    Thread.currentThread().join(5000);

    System.out.println(idGeneratorWithAtom.getCount());

    System.out.println("end");
    executorService.shutdown();
  }
}
