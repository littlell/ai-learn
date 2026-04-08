package com.demo.jvm.basics.concurrency;

public class CustomInterrupt extends Thread{

  @Override
  public void interrupt() {
    System.out.println("do some custom clean job.");
    super.interrupt();
  }

  @Override
  public void run() {
    try {
      sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("run");
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new CustomInterrupt();

    thread.run();

    Thread.sleep(5000);

    thread.interrupt();
  }
}
