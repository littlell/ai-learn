package com.demo.jvm.basics.zookeeper.curator;

import java.io.EOFException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.RetryOneTime;


public class LeaderLatchRecipe {

  /**
   * Created by xialei on 2016/12/15.
   */
  public static void main(String[] args) throws Exception {
    new Thread(
        new Runnable() {

          private final String connectString = "localhost:2181";
          private final String zkPath = "/LeaderLatchRecipe";

          @Override
          public void run() {
            CuratorFramework client =
                CuratorFrameworkFactory.newClient(connectString, new RetryOneTime(1));
            client.start();
            LeaderLatch leaderLatch = new LeaderLatch(client, zkPath);
            try {
              leaderLatch.start();
              while (true) {
                if (leaderLatch.hasLeadership()) {
                  System.out.println("I'm the leader.");
                  System.out.println("doing job for 5 seconds.");
                  Thread.sleep(5000);
                  // 只能通过close放弃leader， 不太合理。。
                  leaderLatch.close();
                  return;
                } else {
                  System.out.println("I'm a follower, waiting for leadership....");
                  leaderLatch.await();
                }
              }
            } catch (InterruptedException e) {
              e.printStackTrace();
            } catch (EOFException e) {
              e.printStackTrace();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        })
        .start();
  }
}
