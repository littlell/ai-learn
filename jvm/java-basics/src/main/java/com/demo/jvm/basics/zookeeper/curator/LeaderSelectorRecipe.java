package com.demo.jvm.basics.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.CancelLeadershipException;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.RetryOneTime;

public class LeaderSelectorRecipe {

  /**
   * Created by xialei on 2016/12/15.
   */
  public static void main(String[] args) throws Exception {
    new Thread(
        new Runnable() {

          private final String connectUrl = "localhost:2181";
          private final String zkPath = "/LeaderSelectorRecipe";

          @Override
          public void run() {
            final CuratorFramework client =
                CuratorFrameworkFactory.newClient(connectUrl, new RetryOneTime(1));
            client.start();
            LeaderSelectorListener leaderSelectorListener = new LeaderSelectorListener() {
              @Override
              public void takeLeadership(CuratorFramework curatorFramework)
                  throws Exception {
                System.out.println("I'm the leader.");
                System.out.println("doing job for 10 seconds.");
                System.out.println(Thread.currentThread().getName());
                for (int i = 0; i < 10; i++) {
                  Thread.sleep(1000);
                  System.out.println(i);
                }
                synchronized (client) {
                  client.notify();
                }
              }

              @Override
              public void stateChanged(
                  CuratorFramework curatorFramework, ConnectionState connectionState) {
                if (!connectionState.isConnected()) {
                  throw new CancelLeadershipException();
                }
              }
            };
            final LeaderSelector leaderSelector =
                new LeaderSelector(
                    client,
                    zkPath,
                    leaderSelectorListener);

            leaderSelector.start();

            while (true) {
              try {
                synchronized (client) {
                  System.out.println(Thread.currentThread().getName());
                  leaderSelector.autoRequeue();
                  client.wait();
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
          }
        })
        .start();
  }
}
