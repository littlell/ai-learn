package com.demo.jvm.spring.boot.multicache.controller;

import com.demo.jvm.spring.boot.multicache.service.CacheService;
import com.demo.jvm.spring.boot.multicache.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

  @Autowired
  private CacheService cacheService;

  @GetMapping("/cacheable/{key}")
  public String cacheable(@PathVariable String key) {
    return cacheService.cacheable(key);
  }

  @GetMapping("/cache-put/{key}")
  public String cachePut(@PathVariable String key) {
    return cacheService.cachePut(key);
  }

  @GetMapping("/clean-cache-item/{key}")
  public String cleanCacheItem(@PathVariable String key) {
    cacheService.clearCacheItem(key);
    return "success";
  }

  @GetMapping("/clean-cache")
  public String cleanCache() {
    cacheService.clearCache();
    return "success";
  }

  @GetMapping("/cache-object")
  public String cacheObject() {
    User user = cacheService.cacheObject();

    return "OK";
  }
}