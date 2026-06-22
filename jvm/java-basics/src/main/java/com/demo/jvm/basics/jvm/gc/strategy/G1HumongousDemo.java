package com.demo.jvm.basics.jvm.gc.strategy;

/**
 * 演示 G1 GC 的 Humongous 对象分配机制。
 *
 * 编译（从项目根目录 ai-learn 执行）：
 *   cd jvm/java-basics && mvn compile -q
 *
 * 运行（从 jvm/java-basics 目录执行）：
 *   java -Xms40M -Xmx40M "-Xlog:gc*,gc+age=trace" -cp target/classes com.demo.jvm.basics.jvm.gc.strategy.G1HumongousDemo
 *
 * 注意：fish shell 下含 * 的参数需加引号，如 "-Xlog:gc*,gc+age=trace"
 *
 * Humongous 规则：
 *   对象大小 > Region Size / 2 时，直接分配到 Humongous 区，完全绕过 Eden。
 *   G1 默认 Region Size 由堆大小自动计算（1M~32M，取 2 的幂次）。
 *   堆 40M 时 Region Size = 1M，阈值 = 512K。
 *
 * 运行参数：
 *   -Xms40M -Xmx40M "-Xlog:gc*,gc+age=trace"
 *
 * 观察目标：
 *   1. 小对象（256K）走 Eden，触发 Young GC 时出现在 age table
 *   2. 大对象（512K+）直接进 Humongous 区，GC 原因显示 "G1 Humongous Allocation"
 *   3. Humongous regions 数量随大对象分配而增长
 */
public class G1HumongousDemo {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        System.out.println("=== 阶段一：小对象走 Eden ===");
        smallObjectsPhase();

        System.out.println("=== 阶段二：大对象走 Humongous ===");
        humongousPhase();
    }

    /**
     * 分配小于 512K 的对象，走正常 Eden 路径。
     * 观察：GC 原因为 "Allocation Failure"，age table 中有对象年龄记录。
     */
    static void smallObjectsPhase() {
        byte[] a, b, c, d, e;
        a = new byte[256 * 1024];  // 256K < 512K，进 Eden
        b = new byte[256 * 1024];
        c = new byte[256 * 1024];
        d = new byte[256 * 1024];
        e = new byte[256 * 1024];
        // 保持引用，让这批对象在 GC 时进入 Survivor
        System.out.println("小对象分配完毕，共 " + (5 * 256) + "KB");
        allocWaste(100);  // 100 × 256K = 25MB，足够填满 Eden(~19M) 触发 Young GC
        // 引用置空，下次 GC 时回收
        a = b = c = d = e = null;
    }

    /**
     * 分配大于等于 512K 的对象，触发 Humongous 分配。
     * 观察：
     *   - GC 原因变为 "G1 Humongous Allocation"
     *   - "Humongous regions" 数量增加
     *   - age table 中不会出现这些对象（它们不在 Survivor 里）
     */
    static void humongousPhase() {
        byte[] h1 = new byte[512 * 1024];       // 512K，刚好踩阈值，进 Humongous
        System.out.println("h1(512K) 分配完毕");

        byte[] h2 = new byte[_1MB];             // 1MB，明显的 Humongous 对象
        System.out.println("h2(1MB) 分配完毕");

        byte[] h3 = new byte[2 * _1MB];         // 2MB，占用 3 个 Region
        System.out.println("h3(2MB) 分配完毕");

        // 用小对象填 Eden，触发 GC，观察 Humongous regions 数量（h1/h2/h3 还存活）
        allocWaste(80);
        System.out.println("第一轮 GC 完成，h1/h2/h3 仍存活，Humongous regions 应保持不变");

        // 引用置空，再触发一次 GC，观察 Humongous regions 下降
        h1 = h2 = h3 = null;
        allocWaste(80);
        System.out.println("第二轮 GC 完成，h1/h2/h3 已回收，Humongous regions 应降为 0");
    }

    /**
     * 分配短命废弃对象，目的是制造 GC 压力触发回收，方法返回后全部变为垃圾。
     */
    static void allocWaste(int count) {
        // 用 256K 小对象填 Eden，避免踩 Humongous 阈值（>512K）
        for (int i = 0; i < count; i++) {
            byte[] waste = new byte[256 * 1024];
        }
    }
}
