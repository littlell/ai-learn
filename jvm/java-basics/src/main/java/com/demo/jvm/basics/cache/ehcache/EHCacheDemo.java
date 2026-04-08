package com.demo.jvm.basics.cache.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.time.Duration;

public class EHCacheDemo {

  public static final String CACHE = "myCache";
  public static final String DATA_DIR = "/tmp/mycache";
  public static final int TTL_SECONDS = 30;
  public static final int HEAP_SIZE_MB = 1;
  public static final int OFF_HEAP_SIZE_MB = 10;
  public static final int DISK_SIZE_GB = 1;

  public static void main(String[] args) throws InterruptedException {

    CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
        .withCache(CACHE, CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Integer.class, ResourcePoolsBuilder.newResourcePoolsBuilder()
                // 指定堆内缓存1MB
                .heap(HEAP_SIZE_MB, MemoryUnit.MB)
                // 指定堆外缓存10MB
                .offheap(OFF_HEAP_SIZE_MB, MemoryUnit.MB)
                // 指定磁盘缓存1GB
                .disk(DISK_SIZE_GB, MemoryUnit.GB, true))
            .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(TTL_SECONDS)))
            .build())
        // 指定持久化磁盘路径
        .with(CacheManagerBuilder.persistence(DATA_DIR))
        .build(true);

    Cache<String, Integer> cache = cacheManager.getCache(CACHE, String.class, Integer.class);
    Integer value1 = cache.get("key1");
    System.out.println("Value 1: " + value1);
    cache.put("key1", 1);
    cacheManager.close();
  }
}
