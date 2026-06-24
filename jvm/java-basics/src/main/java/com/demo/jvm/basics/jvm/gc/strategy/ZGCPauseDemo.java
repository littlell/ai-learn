/*
 * 编译：cd jvm/java-basics && mvn compile -q
 * 运行（从 jvm/java-basics 目录执行）：
 *
 * 场景1 — ZGC，观察停顿时间：
 *   java -Xms128M -Xmx128M -XX:+UseZGC "-Xlog:gc*:file=/tmp/zgc.log:time,uptime,level,tags" -cp target/classes com.demo.jvm.basics.jvm.gc.strategy.ZGCPauseDemo
 *   然后查看停顿：grep "Pause" /tmp/zgc.log
 *
 * 场景2 — G1，相同堆大小，对比停顿时间：
 *   java -Xms128M -Xmx128M -XX:+UseG1GC "-Xlog:gc*:file=/tmp/g1pause.log:time,uptime,level,tags" -cp target/classes com.demo.jvm.basics.jvm.gc.strategy.ZGCPauseDemo
 *   然后查看停顿：grep "Pause" /tmp/g1pause.log
 *
 * 对比两份日志中 Pause 行的耗时数字（单位 ms），感受 ZGC 与 G1 的 STW 差距。
 *
 * 注意：fish shell 下含 * 的参数需加引号，如 "-Xlog:gc*:..."
 */
package com.demo.jvm.basics.jvm.gc.strategy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ZGC vs G1 停顿时间对比实验。
 *
 * 核心问题：ZGC 怎么做到停顿 < 1ms？G1 在同等压力下停顿多久？
 *
 * 实验策略：
 *   持续分配中等对象（128KB），滑动窗口保留一批长期存活对象模拟 Old 压力，
 *   其余短命，制造持续 GC 压力，运行 10s 后通过日志对比 Pause 耗时。
 *
 * ZGC 停顿只发生在三个极短 STW 点（Mark Start / Mark End / Relocate Start），
 * 其余阶段（并发标记、并发重定位）与应用线程同时运行。
 * G1 的 STW 包含完整的 Young GC Evacuation，耗时随存活对象量增长。
 */
public class ZGCPauseDemo {

    // 32K int × 4 字节 = 128KB，低于 G1 默认 Region(1MB) 的 Humongous 阈值，走正常晋升路径
    private static final int OBJECT_INTS = 32 * 1024;

    // 固定窗口保留存活对象，模拟 Old 区长期存活压力
    private static final int WINDOW_SIZE = 80;
    private static final Deque<int[]> window = new ArrayDeque<>(WINDOW_SIZE);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始压测，堆 128M，对象 128KB/个，运行 10s...");
        System.out.println("GC 日志写入 /tmp/zgc.log 或 /tmp/g1pause.log");

        long start = System.currentTimeMillis();
        long rounds = 0;

        while (System.currentTimeMillis() - start < 10_000) {
            int[] obj = new int[OBJECT_INTS];

            // 滑动窗口：满了弹出最旧的，保持 WINDOW_SIZE 个对象长期存活
            if (window.size() >= WINDOW_SIZE) {
                window.pollFirst();
            }
            window.addLast(obj);

            if (++rounds % 500 == 0) {
                long elapsed = System.currentTimeMillis() - start;
                System.out.printf("  %4dms  rounds=%d  window=%d%n", elapsed, rounds, window.size());
            }
        }

        System.out.println("完成，总轮数: " + rounds);
        System.out.println("ZGC 停顿：grep \"Pause\" /tmp/zgc.log");
        System.out.println("G1  停顿：grep \"Pause\" /tmp/g1pause.log");
    }
}
