/*
 * 编译：cd jvm/java-basics && mvn compile -q
 * 运行（从 jvm/java-basics 目录执行）：
 *   java -cp target/classes com.demo.jvm.basics.cs.network.HttpRequestTimingDemo
 *
 * 对比场景（换成只支持 HTTP 的地址，观察少了 TLS 那一段）：
 *   java -cp target/classes com.demo.jvm.basics.cs.network.HttpRequestTimingDemo http://example.com/
 */
package com.demo.jvm.basics.cs.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

/**
 * 演示一次 HTTP/HTTPS 请求的各阶段耗时：
 *   DNS 解析 → TCP 建连 → (TLS 握手) → 发送请求 → 首字节到达(TTFB)
 *
 * 关键观察：为什么 HTTPS 比 HTTP 慢？多出来的时间花在哪里？
 */
public class HttpRequestTimingDemo {

    private static final String DEFAULT_URL = "https://www.baidu.com/";

    public static void main(String[] args) throws Exception {
        String target = args.length > 0 ? args[0] : DEFAULT_URL;
        System.out.println("目标地址: " + target);
        System.out.println("=".repeat(50));

        // ── 阶段 1：DNS 解析 ────────────────────────────────
        URL url = new URL(target);
        String host = url.getHost();

        long t0 = System.currentTimeMillis();
        InetAddress address = InetAddress.getByName(host);   // 触发 DNS 查询
        long t1 = System.currentTimeMillis();

        System.out.printf("[DNS 解析]    %s → %s  耗时: %d ms%n",
                host, address.getHostAddress(), t1 - t0);

        // ── 阶段 2+3：TCP 建连 + TLS 握手（connect() 合并完成）──
        // HttpURLConnection.connect() 内部：先 TCP 三次握手，若是 HTTPS 再做 TLS 握手
        // JDK 没有暴露单独的 TCP/TLS 分离计时，这里合并观察
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        // 关闭连接复用，强制每次都走完整握手，方便观察
        conn.setRequestProperty("Connection", "close");

        long t2 = System.currentTimeMillis();
        conn.connect();   // TCP 三次握手 + TLS 握手（HTTPS）在此完成
        long t3 = System.currentTimeMillis();

        boolean isTls = target.startsWith("https");
        System.out.printf("[TCP+%s 握手] 耗时: %d ms%n",
                isTls ? "TLS" : "---", t3 - t2);

        // ── 阶段 4：首字节到达（TTFB）──────────────────────
        // getResponseCode() 触发发送请求头，并阻塞直到第一个响应字节到达
        long t4 = System.currentTimeMillis();
        int statusCode = conn.getResponseCode();
        long t5 = System.currentTimeMillis();

        System.out.printf("[TTFB]        HTTP %d  耗时: %d ms%n", statusCode, t5 - t4);

        // ── 阶段 5：读取响应体 ──────────────────────────────
        long t6 = System.currentTimeMillis();
        int bytes = drain(conn.getInputStream());
        long t7 = System.currentTimeMillis();

        System.out.printf("[响应体读取]  %d bytes  耗时: %d ms%n", bytes, t7 - t6);

        conn.disconnect();

        // ── 汇总 ────────────────────────────────────────────
        System.out.println("=".repeat(50));
        System.out.printf("全程总耗时: %d ms%n", t7 - t0);
        System.out.println();
        printLayerMapping(isTls);
    }

    private static int drain(InputStream in) throws IOException {
        byte[] buf = new byte[4096];
        int total = 0, n;
        while ((n = in.read(buf)) != -1) {
            total += n;
        }
        in.close();
        return total;
    }

    private static void printLayerMapping(boolean isTls) {
        System.out.println("【协议层对应关系】");
        System.out.println("  应用层  HTTP/HTTPS  ← 你写的 GET 请求");
        if (isTls) {
            System.out.println("  表示层  TLS         ← 证书验证 + 对称密钥协商");
        }
        System.out.println("  传输层  TCP         ← 三次握手，保证有序可靠");
        System.out.println("  网络层  IP          ← 路由寻址");
        System.out.println("  链路层  以太网/WiFi ← 实际物理传输");
        System.out.println();
        System.out.println("DNS 查询是独立的：UDP 53 端口，发生在 TCP 建连之前");
    }
}
