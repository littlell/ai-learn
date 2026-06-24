/*
 * 编译：cd jvm/java-basics && mvn compile -q
 * 运行（从 jvm/java-basics 目录执行）：
 *
 * 场景1 — 默认 G1，观察 Eden 分配与 Young GC：
 *   java -Xms20M -Xmx20M -XX:+UseG1GC "-Xlog:gc*,gc+age=trace" -cp target/classes com.demo.jvm.basics.jvm.gc.strategy.AllocEdenFirst
 *
 * 场景2 — 调小 G1 Region，观察分配行为变化（Region=512K，Humongous 阈值=256K）：
 *   java -Xms20M -Xmx20M -XX:+UseG1GC -XX:G1HeapRegionSize=512k "-Xlog:gc*,gc+age=trace" -cp target/classes com.demo.jvm.basics.jvm.gc.strategy.AllocEdenFirst
 *
 * 注意：fish shell 下含 * 的参数需加引号，如 "-Xlog:gc*,gc+age=trace"
 */
package com.demo.jvm.basics.jvm.gc.strategy;

/**
 * 对象内存优先从 Eden 区分配，Eden 空间不足时触发 Young GC。
 *
 * allocation1/2/3 各 2MB 先填 Eden；
 * allocation4 分配 4MB 时 Eden 放不下，触发 Young GC，
 * 观察日志中 "Pause Young" 事件与各区域大小变化。
 */
public class AllocEdenFirst {
  private static final int _1MB = 1024 * 1024;

  public static void main(String[] args) throws InterruptedException {
    byte[] allocation1, allocation2, allocation3, allocation4;

    System.out.println("start allocate 1");
    allocation1 = new byte[2 * _1MB];
    System.out.println("start allocate 2");
    allocation2 = new byte[2 * _1MB];
    System.out.println("start allocate 3");
    allocation3 = new byte[2 * _1MB];
    System.out.println("start allocate 4");
    allocation4 = new byte[4 * _1MB];
  }
}
