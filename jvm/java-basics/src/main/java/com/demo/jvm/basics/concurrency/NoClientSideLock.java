package com.demo.jvm.basics.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class NoClientSideLock {
  private List<Integer> list =
      new ArrayList<Integer>(10000) {
        {
          for (int i = 0; i < 10000; i++) {
            add(i, i);
          }
        }
      };

  /**
   * 获取最后一个元素.
   *
   * @return 元素
   */
  public Integer getLastItem() {
    //        synchronized (list) {
    if (list.isEmpty()) {
      return -1;
    }
    int size = list.size() - 1;
    Integer item = list.get(size);
    System.out.println("Thread: " + Thread.currentThread().getName() + " query-> " + item);
    return item;
    //        }
  }

  /**
   * 删除最后一个元素.
   *
   * @return 元素
   */
  public Integer deleteLastItem() {
    //        synchronized (list) {
    if (list.isEmpty()) {
      return -1;
    }
    int size = list.size() - 1;
    System.out.println("Thread: " + Thread.currentThread().getName() + " delete");
    list.remove(size);
    return 1;
    //        }
  }

  /**
   * 没有客户端锁， 会出现并发问题.
   *
   * @throws ExecutionException when thread error
   * @throws InterruptedException when thread error
   */
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    NoClientSideLock noClientSideLock = new NoClientSideLock();

    ExecutorService executorService = new ScheduledThreadPoolExecutor(2);

    List<Callable<Integer>> callableList =
        new ArrayList<Callable<Integer>>() {
          {
            add(
                () -> {
                  while (true) {
                    Integer item = noClientSideLock.deleteLastItem();
                    if (item == -1) {
                      break;
                    }
                  }
                  return 0;
                });
            add(
                () -> {
                  while (true) {
                    Integer item = null;
                    try {
                      item = noClientSideLock.getLastItem();
                    } catch (Exception e) {
                      e.printStackTrace();
                    }
                    if (null != item && item == -1) {
                      break;
                    }
                  }
                  return 0;
                });
          }
        };
    List<Future<Integer>> futures = executorService.invokeAll(callableList);
    for (Future<Integer> future : futures) {
      future.get();
    }

    Thread.currentThread().join(5000);
    executorService.shutdown();
  }
}
