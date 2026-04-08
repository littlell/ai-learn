package com.demo.jvm.basics.zookeeper.curator;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

public class Client {

  /** Created by xialei on 2016/12/15. */
  public static void main(String[] args) throws Exception {
    String serverUrl = "localhost:2181";
    CuratorZookeeperClient client =
        new CuratorZookeeperClient(serverUrl, 10000, 10000, null, new RetryOneTime(1));
    client.start();
    try {
      client.blockUntilConnectedOrTimedOut();
      String path =
          client
              .getZooKeeper()
              .create(
                  "/test_znode", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    } finally {
      client.close();
    }
  }
}
