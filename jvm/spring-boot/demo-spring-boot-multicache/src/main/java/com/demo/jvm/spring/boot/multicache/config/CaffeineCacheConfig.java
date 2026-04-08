package com.demo.jvm.spring.boot.multicache.config;

import com.github.benmanes.caffeine.cache.Caffeine;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "caffeine")
public class CaffeineCacheConfig {

  @Bean
  public CaffeineCacheManager caffeineCacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
        // 设置最大缓存项数
        .maximumSize(10000)
        // 设置写入后10分钟过期
        .expireAfterWrite(10, TimeUnit.MINUTES);
    cacheManager.setCaffeine(caffeine);
    System.out.println("CaffeineCacheConfig init.");
    return cacheManager;
  }
}
