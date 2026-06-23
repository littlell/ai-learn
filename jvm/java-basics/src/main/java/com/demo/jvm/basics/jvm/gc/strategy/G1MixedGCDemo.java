/*
 * 编译：cd jvm/java-basics && mvn compile -q
 * 运行（从 jvm/java-basics 目录执行）：
 *
 * 场景1 — 默认 IHOP=45%，观察完整 Young→ConcurrentMark→Mixed GC 链路：
 *   java -Xms60M -Xmx60M -XX:+UseG1GC "-Xlog:gc*:file=/tmp/g1mixed.log:time,uptime,level,tags" -cp target/classes com.demo.jvm.basics.jvm.gc.strategy.G1MixedGCDemo
 *   然后查看日志：grep -E "GC\(|Mixed|concurrent-mark|Pause" /tmp/g1mixed.log | head -40
 *
 * 场景2 — 降低 IHOP=20%，让 Mixed GC 更早触发（对比场景1的触发时机）：
 *   java -Xms60M -Xmx60M -XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=20 "-Xlog:gc*:file=/tmp/g1mixed_ihop20.log:time,uptime,level,tags" -cp target/classes com.demo.jvm.basics.jvm.gc.strategy.G1MixedGCDemo
 *   然后查看日志：grep -E "GC\(|Mixed|concurrent-mark|Pause" /tmp/g1mixed_ihop20.log | head -40
 *
 * 关注日志中：
 *   - "Pause Young (Normal)" → 普通 Young GC
 *   - "concurrent-mark-start" → IHOP 触发并发标记
 *   - "Pause Young (Mixed)" → Mixed GC（同时回收 Young + 部分 Old）
 *   - "To-space exhausted" → Survivor 不够，对象直接晋升 Old
 */
package com.demo.jvm.basics.jvm.gc.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * G1 Mixed GC 触发实验。
 *
 * 核心问题：Old Region 达到什么条件触发 Mixed GC？
 *
 * 实验策略：
 *   分配 512KB 中等对象，每批 5 个后短暂保留再释放一半，
 *   让部分对象在 Survivor 里熬不住，被晋升到 Old Region，
 *   推高 Old 占比，直到触发 Concurrent Mark → Mixed GC。
 */
public class G1MixedGCDemo {

    // 数组元素数量：32K 个 int × 4 字节 = 128KB/对象
    // 60M 堆下 G1 Region ≈ 1MB，Humongous 阈值 512KB，128KB 走正常 Eden→Old 晋升路径
    private static final int OBJECT_SIZE = 32 * 1024;
    // 保留列表，模拟部分对象跨越多次 Young GC 后晋升 Old
    private static final List<int[]> survivor = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("开始分配，堆 60M，对象 512KB/个");
        System.out.println("观察日志中 concurrent-mark-start 和 Pause Young (Mixed)");

        for (int round = 0; round < 200; round++) {
            // 每轮分配 10 个对象，加入保留列表
            for (int i = 0; i < 10; i++) {
                survivor.add(new int[OBJECT_SIZE]);
            }

            // 每隔 5 轮释放前 1/3，保留后 2/3，让大量对象持续存活撑过多次 Young GC
            // 存活对象在 Survivor 放不下时会被晋升到 Old Region，推高 Old 占比
            if (round % 5 == 4 && survivor.size() > 30) {
                int keep = survivor.size() * 2 / 3;
                survivor.subList(0, survivor.size() - keep).clear();
            }

            // 去掉 sleep，让分配尽量快，Eden 快速填满触发更多 Young GC
            if (round % 20 == 19) {
                System.out.printf("Round %d: survivor list size = %d objects%n",
                        round + 1, survivor.size());
            }
        }

        // 保持引用存活，防止被过早回收，确保 Old 填充效果
        System.out.println("完成分配，survivor 存活对象数: " + survivor.size());
        System.out.println("检查 /tmp/g1mixed.log 查看完整 GC 事件序列");
    }
}
