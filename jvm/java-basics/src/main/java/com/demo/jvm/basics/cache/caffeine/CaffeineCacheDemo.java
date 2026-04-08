package com.demo.jvm.basics.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineCacheDemo {

  public static void main(String[] args) {
    // 创建缓存
    Cache<String, String> cache = Caffeine.newBuilder()
        // 设置条目的存活时间为5分钟
        .expireAfterWrite(5, TimeUnit.MINUTES)
        // 设置最大缓存容量为100
        .maximumSize(100)
        // 打开数据收集功能
        .recordStats()
        .build();

    // 将数据放入缓存
    cache.put("key1", "value1");
    cache.put("key2", "value2");

    // 从缓存中获取数据
    String value1 = cache.getIfPresent("key1");
    String value2 = cache.getIfPresent("key2");
    System.out.println("Value 1: " + value1);
    System.out.println("Value 2: " + value2);

    // 从缓存中移除数据
    cache.invalidate("key2");
    // 再次尝试获取已移除的数据
    String removedValue = cache.getIfPresent("key2");
    // Output: null
    System.out.println("Removed Value: " + removedValue);


    // 查询缓存的命中率
    System.out.println(cache.stats().hitRate());
    // 被驱逐的缓存数量
    System.out.println(cache.stats().evictionCount());
    // 新值被载入的平均耗时
    System.out.println(cache.stats().averageLoadPenalty());
  }
}
