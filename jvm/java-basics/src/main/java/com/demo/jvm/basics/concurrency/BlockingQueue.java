package com.demo.jvm.basics.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * LinkedBlockingQueue and ArrayBlockingQueue are FIFO queues. PriorityBlockingQueue can compare
 * elements according to their natural order. SynchronousQueue 是这样 一种阻塞队列，其中每个 put 必须等待一个 take.
 */
class BlockingQueue {

  public static void main(String[] args) throws InterruptedException {
    // 容量不限的队列
    LinkedBlockingQueue linkedBlockingQueueUnLimited = new LinkedBlockingQueue();

    linkedBlockingQueueUnLimited.put("aaaaaa");

    linkedBlockingQueueUnLimited.put("bbbbbb");

    System.out.println(linkedBlockingQueueUnLimited.poll());

    System.out.println(linkedBlockingQueueUnLimited.poll());

    System.out.println(linkedBlockingQueueUnLimited.peek());

    // 容量显示的队列
    LinkedBlockingQueue linkedBlockingQueueLimited = new LinkedBlockingQueue(2);
    linkedBlockingQueueLimited.put("1");
    linkedBlockingQueueLimited.put("2");

    System.out.println(linkedBlockingQueueLimited.poll());
    System.out.println(linkedBlockingQueueLimited.poll());
    System.out.println(linkedBlockingQueueLimited.poll());

    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(2);
    arrayBlockingQueue.add("i");
    arrayBlockingQueue.add("ii");
    System.out.println(arrayBlockingQueue.poll());
    System.out.println(arrayBlockingQueue.poll());
    System.out.println(arrayBlockingQueue.poll());

    PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
    priorityBlockingQueue.add(new User("a"));
    priorityBlockingQueue.add(new User("aaaa"));
    priorityBlockingQueue.add(new User("aa"));
    System.out.println(priorityBlockingQueue.poll());
    System.out.println(priorityBlockingQueue.poll());
    System.out.println(priorityBlockingQueue.poll());
    System.out.println(priorityBlockingQueue.poll());

    SynchronousQueue synchronousQueue = new SynchronousQueue();
    new Thread(() -> {
      try {
        while (true) {
          System.out.println(synchronousQueue.take());
          Thread.currentThread().sleep(5000);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    synchronousQueue.put("k");
    synchronousQueue.put("l");
    synchronousQueue.put("m");

    // Deque是一种双向队列， 可在队列头和尾进行插入和删除， 并且消费者有各自的队列通道， 消费者之间可以互相偷取消费任务， 平衡消费行为.
    LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque<>();
    linkedBlockingDeque.add(1);
    linkedBlockingDeque.add(2);
    System.out.println(linkedBlockingDeque.getFirst());
    System.out.println(linkedBlockingDeque.getLast());
  }

  static class User implements Comparable<User> {

    private String name;

    public User(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "User{" + "name='" + name + '\'' + '}';
    }

    @Override
    public int compareTo(User o) {
      if (this.name.length() > o.name.length()) {
        return 1;
      }
      return 0;
    }
  }
}
