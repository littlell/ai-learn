package com.demo.jvm.spring.boot.multicache.config;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "HAZELCAST")
public class HazelcastCacheConfig {

  @Bean
  public HazelcastInstance hazelcastInstance() {
    return Hazelcast.newHazelcastInstance();
  }

  @Bean
  public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
    System.out.println("HazelcastCacheConfig init.");
    return new HazelcastCacheManager(hazelcastInstance);
  }
}

