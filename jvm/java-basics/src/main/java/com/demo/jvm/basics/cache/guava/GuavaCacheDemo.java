package com.demo.jvm.basics.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;


import java.util.concurrent.TimeUnit;

public class GuavaCacheDemo {

  public static void main(String[] args) {
    // 创建缓存
    Cache<String, String> cache = CacheBuilder.newBuilder()
        // TTL
        .expireAfterWrite(5, TimeUnit.MINUTES)
        // 设置最大缓存容量为100
        .maximumSize(100).build();

    // 将数据放入缓存
    cache.put("key1", "value1");

    // 从缓存中获取数据
    String value1 = cache.getIfPresent("key1");
    String value2 = cache.getIfPresent("key2");
    System.out.println("Value 1: " + value1);
    System.out.println("Value 2: " + value2);

    // 从缓存中移除数据
    cache.invalidate("key2");
    // 再次尝试获取已移除的数据
    String removedValue = cache.getIfPresent("key2");
    System.out.println("Removed Value: " + removedValue);  // Output: null
  }
}
