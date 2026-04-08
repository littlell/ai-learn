package com.demo.jvm.basics.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class StringDemo {
  public static void main(String[] args) {
    // 连接到Redis服务器
    Jedis jedis = new Jedis("localhost", 6379);

    // 输入密码
    jedis.auth("123456");

    // 设置字符串值
    jedis.set("key", "value");

    // 获取字符串值
    String value = jedis.get("key");
    System.out.println("Value: " + value);

    // 追加字符串
    jedis.append("key", " appended");

    // 获取追加后的字符串
    value = jedis.get("key");
    System.out.println("Value after append: " + value);

    // 关闭Redis连接
    jedis.close();
  }
}
