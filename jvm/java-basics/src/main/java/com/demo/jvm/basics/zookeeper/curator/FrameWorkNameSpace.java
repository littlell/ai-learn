package com.demo.jvm.basics.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;

public class FrameWorkNameSpace {
  /** Created by xialei on 2016/12/15. */
  public static void main(String[] args) throws Exception {
    String serverUrl = "localhost:2181";
    CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
    CuratorFramework client =
        builder.connectString(serverUrl).namespace("app").retryPolicy(new RetryOneTime(1)).build();
    client.start();
    try {
      String path =
          client.create().withMode(CreateMode.PERSISTENT).forPath("/test_znode", "".getBytes());
    } finally {
      client.close();
    }
  }
}
