package com.demo.jvm.spring.boot.config;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {
  private final StringRedisTemplate stringRedisTemplate;

  public CacheController(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate;
  }

  @GetMapping("/set")
  public String set(@RequestParam String key, @RequestParam String value) {
    stringRedisTemplate.opsForValue().set(key, value);
    return "success";
  }

  @GetMapping("/get")
  public String get(@RequestParam String key) {
    return stringRedisTemplate.opsForValue().get(key);
  }
}
