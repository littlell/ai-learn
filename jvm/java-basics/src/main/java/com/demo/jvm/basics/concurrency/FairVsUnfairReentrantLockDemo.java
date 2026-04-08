package com.demo.jvm.basics.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 对比公平 / 非公平 ReentrantLock：释放锁瞬间，新线程是否可能先于队列中的等待线程拿到锁。
 */
public class FairVsUnfairReentrantLockDemo {

  private static Runnable waiter(
      ReentrantLock lock, CountDownLatch ready, CountDownLatch done, String line) {
    return () -> {
      try {
        ready.await();
        lock.lock();
        try {
          System.out.println(line);
        } finally {
          lock.unlock();
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } finally {
        done.countDown();
      }
    };
  }

  private static void run(boolean fair, String prefix) throws InterruptedException {
    ReentrantLock lock = new ReentrantLock(fair);
    CountDownLatch ready = new CountDownLatch(1);
    CountDownLatch done = new CountDownLatch(3);

    lock.lock();
    try {
      new Thread(waiter(lock, ready, done, prefix + " acquired: T1-waiting")).start();
      new Thread(waiter(lock, ready, done, prefix + " acquired: T2-waiting")).start();
      Thread.sleep(50);
      new Thread(
              () -> {
                try {
                  ready.await();
                  lock.lock();
                  try {
                    System.out.println(prefix + " acquired: T3-late");
                  } finally {
                    lock.unlock();
                  }
                } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
                } finally {
                  done.countDown();
                }
              })
          .start();
    } finally {
      lock.unlock();
    }

    ready.countDown();
    done.await();
    System.out.println("---");
  }

  public static void main(String[] args) throws InterruptedException {
    run(false, "[unfair]");
    run(true, "[fair]   ");
  }
}
