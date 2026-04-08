package com.demo.jvm.basics.concurrency;

public class InterruptedExceptionUsage {

  /**
   * 线程中断， 不会打印， 会被外部中断执行流程.
   */
  public static void main(String[] args) throws InterruptedException {

    Runnable runnable = () -> {
      while (true) {
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("be interrupted, do some clean job.");
          break;
        } else {
          System.out.println("wa.");
        }
      }
    };
    Thread thread = new Thread(runnable);

    thread.start();

    Thread.currentThread().join(2000);

    thread.interrupt();
  }
}
