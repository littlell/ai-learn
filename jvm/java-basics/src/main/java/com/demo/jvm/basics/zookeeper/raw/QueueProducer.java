package com.demo.jvm.basics.zookeeper.raw;

import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;


public class QueueProducer {
  private static String hostPort = "localhost:2181";
  private static String queuePath = "/_QUEUE_";
  static ZooKeeper zk;

  /** Created by xialei on 2016/12/8. */
  public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
    zk = new ZooKeeper(hostPort, 2000, null);

    if (zk.exists(queuePath, null) == null) {
      zk.create(queuePath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
    while (true) {
      Thread.currentThread().sleep(1000);
      zk.create(
          queuePath + "/queue-",
          "".getBytes(),
          ZooDefs.Ids.OPEN_ACL_UNSAFE,
          CreateMode.PERSISTENT_SEQUENTIAL);
    }
  }
}
