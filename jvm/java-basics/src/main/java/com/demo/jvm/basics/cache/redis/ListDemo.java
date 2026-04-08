package com.demo.jvm.basics.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class ListDemo {
  public static void main(String[] args) {
    // 连接到Redis服务器
    Jedis jedis = new Jedis("localhost", 6379);

    // 输入密码
    jedis.auth("123456");

    // 将值添加到列表的头部
    jedis.lpush("list", "value1", "value2", "value3");

    // 获取列表范围内的值
    List<String> values = jedis.lrange("list", 0, -1);
    System.out.println("List values: " + values);

    // 获取列表的长度
    long length = jedis.llen("list");
    System.out.println("List length: " + length);

    // 关闭Redis连接
    jedis.close();
  }
}
