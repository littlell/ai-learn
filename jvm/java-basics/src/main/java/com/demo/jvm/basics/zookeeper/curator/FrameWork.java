package com.demo.jvm.basics.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;

public class FrameWork {
  /** Created by xialei on 2016/12/15. */
  public static void main(String[] args) throws Exception {
    String serverUrl = "localhost:2181";
    CuratorFramework client = CuratorFrameworkFactory.newClient(serverUrl, new RetryOneTime(1));
    client.start();
    try {
      String path =
          client.create().withMode(CreateMode.PERSISTENT).forPath("/test_znode", "".getBytes());
    } finally {
      client.close();
    }
  }
}
