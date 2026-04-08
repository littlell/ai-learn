package com.demo.jvm.spring.boot.multicache.service;

import com.demo.jvm.spring.boot.multicache.entity.User;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

  @Cacheable("cache1")
  public String cacheable(String key) {
    System.out.println("Fetching data for key: " + key);
    // 模拟缓慢的数据访问
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "Cached data for " + key;
  }

  @CachePut("cache1")
  public String cachePut(String key) {
    System.out.println("Fetching data for key: " + key);
    // 模拟缓慢的数据访问
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "Cached data for " + key;
  }

  @CacheEvict(cacheNames = "cache1", key = "#key")
  public void clearCacheItem(String key) {
    // 执行清除缓存项的逻辑
  }

  @Caching(evict = {@CacheEvict(cacheNames = "cache1", allEntries = true), @CacheEvict(cacheNames = "cache2", allEntries = true)})
  public void clearCache() {
    // 执行清除整个缓存的逻辑
  }

  @Cacheable(value = "cache1", key = "'object'")
  public User cacheObject() {

    System.out.println("Fetching object data");

    User user = new User("foo", 1);

    return user;
  }
}
