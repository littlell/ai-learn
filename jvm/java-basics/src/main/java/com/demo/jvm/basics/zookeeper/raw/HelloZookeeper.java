package com.demo.jvm.basics.zookeeper.raw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;


public class HelloZookeeper {
  /** Created by xialei on 2016/12/8. */
  public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
    String hostPort = "localhost:2181,localhost:2182,localhost:2183";
    String path = "/";

    List<String> zooChildren = new ArrayList<>();
    ZooKeeper zooKeeper = new ZooKeeper(hostPort, 2000, null);

    System.out.println(zooKeeper.getSessionId());

    if (zooKeeper != null) {
      zooChildren = zooKeeper.getChildren(path, false);
      System.out.println("Znodes of '/': ");
      for (String child : zooChildren) {
        System.out.println(child);
      }
    }
  }
}
