package com.demo.jvm.basics.zookeeper.raw;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/** Created by xialei on 2016/12/11. */
public class Election implements Watcher, Runnable {
  private static String hostPort = "localhost:2181";
  private static String zooLockPath = "/_election_";
  ZooKeeper zk;
  String node;

  public Election() throws IOException, KeeperException, InterruptedException {
    zk = new ZooKeeper(hostPort, 2000, this);
  }

  @Override
  public void run() {
    try {
      while (true) {
        Thread.currentThread().sleep(5000);
        System.out.println(Thread.currentThread().getName() + ": 检查是否可以执行操作");
        node =
            zk.create(
                zooLockPath + "/candidate-sessionID-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("创建临时节点: " + node);
        List<String> nodes = zk.getChildren(zooLockPath, false);
        zk.exists(zooLockPath + "/" + Collections.min(nodes), this);
        if (!(zooLockPath + "/" + Collections.min(nodes)).equals(node)) {
          System.out.println("选举失败， 等待..");
          synchronized (this) {
            wait();
          }
        }
        System.out.println("被选为leader， 执行业务操作， 需要十秒");
        Thread.currentThread().sleep(10000);
        zk.delete(node, 0);
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
    if (event.getType() != Event.EventType.NodeDeleted) {
      return;
    }
    List<String> nodes;
    try {
      nodes = zk.getChildren(zooLockPath, false);
      Collections.sort(nodes);
      if (!(zooLockPath + "/" + Collections.max(nodes)).equals(node)) {
        zk.exists(zooLockPath + "/" + Collections.min(nodes), this);
      } else {
        synchronized (this) {
          notify();
        }
      }
    } catch (KeeperException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
    Election lock = new Election();
    lock.run();
  }
}
