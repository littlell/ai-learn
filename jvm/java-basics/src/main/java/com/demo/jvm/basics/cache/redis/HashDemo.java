package com.demo.jvm.basics.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class HashDemo {
  public static void main(String[] args) {
    // 连接到Redis服务器
    Jedis jedis = new Jedis("localhost", 6379);

    // 输入密码
    jedis.auth("123456");

    // 设置散列值
    jedis.hset("hash", "field1", "value1");
    jedis.hset("hash", "field2", "value2");
    jedis.hset("hash", "field3", "value3");

    // 获取散列所有的字段和值
    Map<String, String> hash = jedis.hgetAll("hash");
    System.out.println("Hash values: " + hash);

    // 获取散列指定字段的值
    String value = jedis.hget("hash", "field1");
    System.out.println("Value of field1: " + value);

    // 关闭Redis连接
    jedis.close();
  }
}
