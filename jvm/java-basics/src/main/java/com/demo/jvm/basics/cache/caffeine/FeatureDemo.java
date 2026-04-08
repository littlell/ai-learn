package com.demo.jvm.basics.cache.caffeine;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Logger;

public class FeatureDemo {

  private static final Logger LOGGER = Logger.getLogger("FeatureDemo");
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    Long userId = 666L;

    //获取历史分数
    FutureTask<Integer> historyScoreFutureTask = new FutureTask<>(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        //模拟调用耗时
        Thread.sleep(5000);
        return 80;
      }
    });
    executorService.submit(historyScoreFutureTask);

    //模拟主线程其它操作耗时
    LOGGER.info("Finishing other job..");
    Thread.sleep(1000);

    //获取历史分数
    FutureTask<Integer> mathScoreFutureTask = new FutureTask<>(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        return 100;
      }
    });
    executorService.submit(mathScoreFutureTask);

    //获取历史分数
    Integer hisScore = historyScoreFutureTask.get();
    LOGGER.info("History score " + hisScore);
    //获取数学分数
    Integer mathScore = mathScoreFutureTask.get();
    LOGGER.info("Math score " + mathScore);
    executorService.shutdown();
  }
}
