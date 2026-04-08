package com.demo.jvm.basics.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class SetDemo {
  public static void main(String[] args) {
    // 连接到Redis服务器
    Jedis jedis = new Jedis("localhost", 6379);

    // 输入密码
    jedis.auth("123456");

    jedis.sadd("set", "apple", "banana", "pear");
    jedis.srem("set", "apple");
    System.out.println(jedis.smembers("set"));
    System.out.println(jedis.sismember("set", "pear"));

    // 关闭Redis连接
    jedis.close();
  }
}
