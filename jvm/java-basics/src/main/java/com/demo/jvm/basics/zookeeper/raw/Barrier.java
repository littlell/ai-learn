package com.demo.jvm.basics.zookeeper.raw;

import java.io.IOException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;


/** Created by xialei on 2016/12/8. */
public class Barrier implements Watcher, Runnable {
  private static String hostPort = "localhost:2181";
  private static String zooBarrierPath = "/zk_barrier";
  ZooKeeper zk;

  public Barrier() throws IOException, KeeperException, InterruptedException {
    zk = new ZooKeeper(hostPort, 2000, this);
  }

  @Override
  public void run() {
    try {
      while (true) {
        Thread.currentThread().sleep(5000);
        System.out.println(Thread.currentThread().getName() + ": 检查是否可以执行操作");
        if (zk.exists(zooBarrierPath, this) == null) {
          processBusiness();
        } else {
          System.out.println("存在Barrier， 等待..");
          synchronized (this) {
            wait();
          }
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    } catch (KeeperException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void process(WatchedEvent event) {
    try {
      zk.exists(zooBarrierPath, this);
    } catch (KeeperException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (event.getType() == Event.EventType.NodeDeleted) {
      processBusiness();
      synchronized (this) {
        notify();
      }
    }
  }

  private void processBusiness() {
    System.out.println(Thread.currentThread().getName() + ": do the job");
  }

  public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
    Barrier barrier = new Barrier();
    barrier.run();
  }
}
