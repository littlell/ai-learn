package com.demo.jvm.basics.cache.memcached;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.MemcachedClientIF;

import java.io.IOException;

import redis.clients.jedis.Jedis;

public class MemcachedDemo {

  public static void main(String[] args) throws IOException {

    // 连接到Memcached服务器
    MemcachedClientIF client = new MemcachedClient(AddrUtil.getAddresses("localhost:11211"));

    // 设置缓存值
    client.set("key", 0, "value");

    // 获取缓存值
    Object value = client.get("key");
    System.out.println("Value: " + value);

    // 删除缓存值
    client.delete("key");

    // 关闭Memcached客户端连接
    client.shutdown();
  }
}
