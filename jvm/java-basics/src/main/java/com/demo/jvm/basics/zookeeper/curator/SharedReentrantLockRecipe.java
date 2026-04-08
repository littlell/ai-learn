package com.demo.jvm.basics.zookeeper.curator;

import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryOneTime;

/** Created by xialei on 2016/12/15. */
public class SharedReentrantLockRecipe {
  static class LockRunner implements Runnable, ConnectionStateListener {
    private static final String SERVER_URL = "localhost:2181";
    private static final String ZK_PATH = "/InterProcessMutex";
    private static final int LOCK_WAIT_SECONDS = 5;
    private InterProcessMutex interProcessMutex;

    @Override
    public void run() {
      CuratorFramework client = CuratorFrameworkFactory.newClient(SERVER_URL, new RetryOneTime(1));

      client.start();

      interProcessMutex = new InterProcessMutex(client, ZK_PATH);

      try {
        while (true) {
          if (interProcessMutex.acquire(LOCK_WAIT_SECONDS, TimeUnit.SECONDS)) {
            System.out.println("获取分布式锁");
            System.out.println("处理业务， 需要五秒钟");
            for (int i = 0; i < 5; i++) {
              Thread.sleep(1000);
              System.out.println(i);
            }
          } else {
            System.out.println("等待两秒钟， 再次尝试获取分布式锁.");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
      if (connectionState == ConnectionState.LOST || connectionState == ConnectionState.SUSPENDED) {
        try {
          while (interProcessMutex.isAcquiredInThisProcess()) {
            interProcessMutex.release();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    new Thread(new LockRunner()).start();
  }
}
