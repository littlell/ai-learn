package com.demo.jvm.basics.cache.caffeine;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Logger;

public class CompletableFutureDemo {

  private static final Logger LOGGER = Logger.getLogger("CompletableFutureDemo");

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    //获取历史分数
    CompletableFuture<Integer> historyScoreFutureTask = CompletableFuture.supplyAsync(() -> {
      try {
        //模拟调用耗时
        Thread.sleep(5000);
        return 80;
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    //获取数学分数
    CompletableFuture<Integer> mathScoreFutureTask = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(3000);
        return 100;
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    //打印历史分数
    historyScoreFutureTask.whenComplete((v, t) -> {
      LOGGER.info("History score " + v);
    });

    //打印数学分数
    mathScoreFutureTask.whenComplete((v, t) -> {
      LOGGER.info("Math score " + v);
    });

    //模拟主线程其它操作耗时
    LOGGER.info("Finishing other job..");
    Thread.sleep(10000);
  }
}