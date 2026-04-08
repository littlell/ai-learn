package com.demo.jvm.basics.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapFeature {
  static Map<String, String> map = new ConcurrentHashMap();

  static {
    map.put("a", "a");
    map.put("b", "b");
    map.putIfAbsent("a", "a");
  }

  /** ConcurrentHashMap尽量使用get, put, containsKey, remove. size和isEmpty在并发场景下是不准确的. */
  public static void main(String[] args) {

    // Insert into map only if no value is mapped from c
    map.putIfAbsent("c", "c");

    // Remove only if a is mapped to a
    map.remove("a", "a");

    // Replace value only if b is mapped to b
    map.replace("b", "b", "b1");

    // Replace value only if K is mapped to some value
    map.replace("b", "b2");

    System.out.println(map);
  }
}
