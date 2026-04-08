package com.demo.jvm.basics.generics;

import java.util.ArrayList;
import java.util.List;

public class RawType {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();

    unSafeAdd(list, Integer.valueOf(1));

    // 运行时异常, 类型不安全
    // String s = list.get(0);

    // 以下情况是例外
    System.out.println(List.class);
    // 类字面值（class literals) 必须使用原始类型
    // System.out.println(List<String>.class);

    // instance of使用原始类型
    System.out.println(list instanceof List);
    System.out.println(list instanceof List<?>);
    // 非法, 泛型类型运行时会被擦除
    // System.out.println(list instanceof List<String>);
  }

  // 原始类型
  static void unSafeAdd(List list, Object object) {
    list.add(object);
  }
}
