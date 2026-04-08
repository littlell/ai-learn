package com.demo.jvm.basics.jvm.gc.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池溢出, JDK1.7之后， 逐步弱化了方法区， 将运行时常量池移到了堆中存放 -Xms20m -Xmx20m -Xlog:gc
 */
public class RuntimeConstantPoolOOM {
  public static void main(String[] args) {

    // 使用List保持着常量池的引用， 避免Full GC回收常量池行为
    List<String> list = new ArrayList<>();

    // 10MB 的 PermSize在integer范围内足够产生OOM了
    int i = 0;

    while (true) {
      System.out.println(i);
      list.add(String.valueOf(i++).intern());
    }
  }
}
