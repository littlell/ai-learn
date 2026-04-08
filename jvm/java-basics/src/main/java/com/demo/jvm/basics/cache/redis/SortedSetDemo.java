package com.demo.jvm.basics.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class SortedSetDemo {
  public static void main(String[] args) {
    // 连接到Redis服务器
    Jedis jedis = new Jedis("localhost", 6379);

    // 输入密码
    jedis.auth("123456");

    jedis.zadd("my_sorted_set", 10, "member1");
    jedis.zadd("my_sorted_set", 5, "member2");
    jedis.zadd("my_sorted_set", 15, "member3");

    Set<String> members = jedis.zrange("my_sorted_set", 0, -1);
    System.out.println("Members in the sorted set: " + members);

    // 关闭Redis连接
    jedis.close();
  }
}
