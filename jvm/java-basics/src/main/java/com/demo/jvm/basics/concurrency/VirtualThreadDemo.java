package com.demo.jvm.basics.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadDemo {

  static final int TASK_COUNT = 10_000;
  static final AtomicInteger counter = new AtomicInteger(0);

  public static void main(String[] args) throws InterruptedException {
    System.out.println("=== JDK 21 Virtual Thread vs Platform Thread ===");
    System.out.println("Task count: " + TASK_COUNT);
    System.out.println();

    platformThreadPool();
    virtualThreadPerTask();
  }

  /** 传统平台线程池：FixedThreadPool 100 */
  static void platformThreadPool() throws InterruptedException {
    counter.set(0);
    var start = Instant.now();
    try (var executor = Executors.newFixedThreadPool(100)) {
      for (int i = 0; i < TASK_COUNT; i++) {
        executor.submit(VirtualThreadDemo::ioTask);
      }
    }
    var elapsed = Duration.between(start, Instant.now()).toMillis();
    System.out.println("[Platform Thread Pool] done: " + counter.get() + " tasks, " + elapsed + "ms");
  }

  /** 虚拟线程：每任务一线程 */
  static void virtualThreadPerTask() throws InterruptedException {
    counter.set(0);
    var start = Instant.now();
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      for (int i = 0; i < TASK_COUNT; i++) {
        executor.submit(VirtualThreadDemo::ioTask);
      }
    }
    var elapsed = Duration.between(start, Instant.now()).toMillis();
    System.out.println("[Virtual Thread PerTask] done: " + counter.get() + " tasks, " + elapsed + "ms");
  }

  /** 模拟 IO 阻塞：sleep 10ms */
  static void ioTask() {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    counter.incrementAndGet();
  }
}
