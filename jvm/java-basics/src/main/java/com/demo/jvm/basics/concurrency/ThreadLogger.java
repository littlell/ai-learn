package com.demo.jvm.basics.concurrency;

import java.util.Calendar;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadLogger {

  private LinkedBlockingQueue<String> logQueue;
  private Thread producer;
  private Thread consumer;
  private boolean isShutdown;

  public ThreadLogger() {
    this.logQueue = new LinkedBlockingQueue<>();
  }

  /**
   * constructor.
   */
  public void start() {
    producer = new ProducerThread();
    consumer = new ConsumerThread();
    producer.start();
    consumer.start();
  }

  /**
   * shutdown the service.
   */
  public void stop() {
    System.out.println("receive stop signal.");

    synchronized (this) {
      isShutdown = true;
    }

    producer.interrupt();
    consumer.interrupt();
  }

  private class ConsumerThread extends Thread {

    @Override
    public void run() {
      while (true) {
        try {
          synchronized (this) {
            if (isShutdown && logQueue.size() == 0) {
              System.out.println("消费完毕, 准备退出.");
              break;
            }
          }
          Thread.sleep(3000);
          String logItem = logQueue.take();
          System.out.println("consumes: " + logItem);
        } catch (InterruptedException e) {
          System.out.println("发生中断异常, 不做任何操作, 否则阻塞方法无法执行.");
        }
      }
    }
  }

  private class ProducerThread extends Thread {

    @Override
    public void run() {
      while (true) {
        try {
          if (Thread.currentThread().isInterrupted()) {
            break;
          }
          Thread.sleep(1000);
          String logItem = Calendar.getInstance().getTime().toString();
          System.out.println("produces: " + logItem);
          logQueue.put(logItem);
          synchronized (this) {
          }
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }

  /**
   * main.
   */
  public static void main(String[] args) throws InterruptedException {
    ThreadLogger threadLogger = new ThreadLogger();
    threadLogger.start();
    Thread.sleep(10000);

    threadLogger.stop();
  }
}
