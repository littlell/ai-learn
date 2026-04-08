package com.demo.jvm.spring.boot.errorhandling;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Mono;

@Controller
public class DemoController {

  @Autowired
  private ReactiveStringRedisTemplate redisTemplate;

  @RequestMapping("/demo")
  public ResponseEntity<String> demo() {
    String body = "demo";
    return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping("/new/prefix-path")
  public ResponseEntity<String> newPrefixPath() {
    String body = "new-prefix-path";
    return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping("/rewrite-path-2/demo")
  public ResponseEntity<String> rewritePath2Demo() {
    String body = "rewrite-path-2-demo";
    return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping("/rewrite-path-3-new/rewrite-path-3/demo")
  public ResponseEntity<String> rewritePath3Demo() {
    String body = "rewrite-path-3-demo";
    return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
  }

  @PostMapping("/redis-set")
  public Mono<ResponseEntity<String>> setKey(@RequestParam String key, @RequestParam String value) {
    // 执行异步set操作
    return redisTemplate.opsForValue().set(key, value)
        // 忽略set操作的结果，因为它是Mono<Void>
        .then(Mono.just("Key-value pair set successfully"))
        // 将结果封装为ResponseEntity
        .map(msg -> new ResponseEntity<>(msg, HttpStatus.OK));
  }
}
