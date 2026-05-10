package com.demo.jvm.basics.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicAtomDemo {

  private AtomicInteger inc = new AtomicInteger(0);

  public void increase() {
    inc.incrementAndGet();
  }

  public static void main(String[] args) throws InterruptedException {
    final AtomicAtomDemo demo = new AtomicAtomDemo();

    for (int i = 0; i < 10; i++) {
      new Thread(
          () -> {
            for (int j = 0; j < 100; j++) {
              demo.increase();
            }
          })
          .start();
    }

    Thread.sleep(2000);

    System.out.println("AtomicInteger result: " + demo.inc.get());
    System.out.println("Expected: 1000");
  }
}