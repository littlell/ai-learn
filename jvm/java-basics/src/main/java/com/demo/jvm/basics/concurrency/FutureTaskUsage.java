package com.demo.jvm.basics.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskUsage {

  static class CustomRuntimeException extends RuntimeException {

  }

  static class CustomCheckedException extends Exception {

  }

  /**
   * FutureTask简单使用.
   */
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    FutureTask<Integer> futureTask = new FutureTask<>(() -> {
      System.out.println("线程开始执行.");
      //throw new CustomRuntimeException();
      //throw new RuntimeException("11111");
      //throw new CustomCheckedException();
      //throw new Exception();
      return 0;
    });

    Thread thread = new Thread(futureTask);

    thread.start();

    try {
      Integer result = futureTask.get();
      System.out.println("线程运行正常, 返回结果是: " + result);
    } catch (ExecutionException re) {
      Throwable throwable = re.getCause();
      if (throwable instanceof CustomRuntimeException) {
        System.out.println("自定义运行时异常发生了.");
      } else if (throwable instanceof RuntimeException) {
        System.out.println("普通运行时异常发生了.");
      } else if (throwable instanceof CustomCheckedException) {
        System.out.println("自定义检查异常发生了.");
      } else if (throwable instanceof Exception) {
        System.out.println("普通检查异常发生了.");
      }
    }
  }

}
