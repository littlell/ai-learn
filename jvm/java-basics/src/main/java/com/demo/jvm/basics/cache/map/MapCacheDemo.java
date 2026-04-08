package com.demo.jvm.basics.cache.map;

import java.util.HashMap;
import java.util.Map;

public class MapCacheDemo {

  private Map<String, Object> cache;

  public MapCacheDemo() {
    this.cache = new HashMap<>();
  }

  public void put(String key, Object value) {
    cache.put(key, value);
  }

  public Object get(String key) {
    return cache.get(key);
  }

  public void remove(String key) {
    cache.remove(key);
  }

  public static void main(String[] args) {
    MapCacheDemo cacheDemo = new MapCacheDemo();

    // 将数据放入缓存
    cacheDemo.put("key1", "value1");
    cacheDemo.put("key2", 123);
    cacheDemo.put("key3", true);

    // 从缓存中获取数据
    Object value1 = cacheDemo.get("key1");
    Object value2 = cacheDemo.get("key2");
    Object value3 = cacheDemo.get("key3");

    System.out.println("Value 1: " + value1);
    System.out.println("Value 2: " + value2);
    System.out.println("Value 3: " + value3);

    // 从缓存中移除数据
    cacheDemo.remove("key2");

    // 再次尝试获取已移除的数据
    Object removedValue = cacheDemo.get("key2");
    System.out.println("Removed Value: " + removedValue);  // Output: null
  }
}
