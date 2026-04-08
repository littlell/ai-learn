package com.demo.jvm.basics.generics;

import java.util.HashSet;
import java.util.Set;

public class GenericMethod {

  public static void main(String[] args) {
    Set<String> s1 = Set.of("a", "b");
    Set<String> s2 = Set.of("c", "d");

    System.out.println(union(s1, s2));
  }

  public static <T> Set<T> union(Set<T> t1, Set<T> t2) {
    Set<T> result = new HashSet<T>();
    result.addAll(t1);
    result.addAll(t2);
    return result;
  }
}
