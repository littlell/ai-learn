package com.demo.jvm.basics.zookeeper.raw;

import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class DataWatcher implements Watcher, Runnable {

  private static String hostPort = "localhost:2181";
  private static String zooDataPath = "/MyConfig";
  byte[] zooData = null;
  ZooKeeper zk;

  /**
   * constructor.
   *
   * @throws IOException when null
   * @throws KeeperException when null
   * @throws InterruptedException when null
   */
  public DataWatcher() throws IOException, KeeperException, InterruptedException {
    zk = new ZooKeeper(hostPort, 2000, this);
    if (zk.exists(zooDataPath, this) == null) {
      zk.create(zooDataPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
  }

  @Override
  public void run() {
    try {
      synchronized (this) {
        while (true) {
          wait();
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public void process(WatchedEvent event) {
    System.out.printf("\nEvent Received: %s", event.toString());
    if (event.getType() == Event.EventType.NodeDataChanged) {
      try {
        printData();

      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (KeeperException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * print data.
   *
   * @throws InterruptedException when null
   * @throws KeeperException when null
   */
  public void printData() throws InterruptedException, KeeperException {
    zooData = zk.getData(zooDataPath, this, null);
    String z = new String(zooData);
    System.out.printf("\nCurrent Data @ ZK Path %s: %s", zooDataPath, z);
  }

  /**
   * Created by xialei on 2016/12/8.
   */
  public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
    DataWatcher dataWatcher = new DataWatcher();
    dataWatcher.printData();
    dataWatcher.run();
  }
}
