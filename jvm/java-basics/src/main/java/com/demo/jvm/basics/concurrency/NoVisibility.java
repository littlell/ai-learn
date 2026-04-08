package com.demo.jvm.basics.concurrency;

public class NoVisibility {
  private static boolean ready;
  private static int number;

  private static class ReaderThread extends Thread {
    @Override
    public void run() {
      while (!ready) {
        Thread.yield();
      }
      System.out.println(number);
    }
  }

  /** 可能打印0， 也可能ReaderThread始终不退出. */
  public static void main(String[] args) {
    new ReaderThread().start();
    number = 42;
    ready = true;
  }
}
