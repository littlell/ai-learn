package com.demo.jvm.basics.cache.caffeine;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AsyncCacheDemo {
  public static void main(String[] args) throws InterruptedException {
    AsyncCache<String, String> cache = Caffeine.newBuilder()
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .maximumSize(10_000)
        .buildAsync();

    String key = "key";

    // 查找一个缓存元素， 没有查找到的时候返回null
    CompletableFuture<String> strFeature = cache.get(key, k -> "value");

    strFeature.whenComplete((v, t) -> {
      System.out.println(v);
    });
  }
}
