package com.demo.jvm.basics.generics;

import java.util.HashSet;
import java.util.Set;

public class UnboundedWildcardType {

  public static void main(String[] args) {
    Set<String> s1 = new HashSet<>() {{
      add("abc");
    }};

    Set<String> s2 = new HashSet<>() {{
      add("efg");
    }};

    System.out.println(numElementsInCommon(s1, s2));
    System.out.println(numElementsInCommonWithWildcard(s1, s2));
  }

  static int numElementsInCommon(Set s1, Set s2) {
    int result = 0;
    for (Object o : s1) {
      if (s2.contains(o)) {
        result++;
        // 类型不安全
        s1.add(1);
      }
    }

    return result;
  }

  // 无限制通配符类型
  static int numElementsInCommonWithWildcard(Set<?> s1, Set<?> s2) {
    int result = 0;
    for (Object o : s1) {
      if (s2.contains(o)) {
        result++;
        // 编译错误, 类型安全
        // s1.add(1);
        // 只可以插入null, 不会破坏类型安全
        s1.add(null);
      }
    }

    return result;
  }
}
