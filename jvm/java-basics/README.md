# java-basics（Java 示例）

将原多子模块合并为**一个项目**（artifactId: java-basics），按**代码类型**分包，便于在一个工程里浏览和运行所有 Java 示例。

## 包结构（按类型）

| 包 | 说明 |
|----|------|
| **com.demo.jvm.basics.generics/spi/serialize/jackson/jna** | 语言基础：泛型、SPI、序列化、Jackson、JNA 等 |
| **com.demo.jvm.basics.apache.cxf** | Apache CXF Web 服务客户端 |
| **com.demo.jvm.basics.algorithm** | 算法与数据结构：链表/栈/队列、递归、排序 |
| **com.demo.jvm.basics.bytecode** | 字节码（ASM）示例 |
| **com.demo.jvm.basics.cache** | 缓存：Caffeine、Ehcache、Guava、Redis、Memcached |
| **com.demo.jvm.basics.concurrency** | 并发：线程、线程池、原子类、锁等 |
| **com.demo.jvm.basics.design** | 设计模式（含 pattern 子包） |
| **com.demo.jvm.basics.function** | 函数式、Lambda、Stream |
| **com.demo.jvm.basics.jvm** | JVM：类加载、GC 等 |
| **com.demo.jvm.basics.rabbitmq** | RabbitMQ：helloworld、routing、subscribe、workqueue |
| **com.demo.jvm.basics.reactor** | Project Reactor 响应式示例 |
| **com.demo.jvm.basics.servlet** | Servlet、Filter、Listener 等 |
| **com.demo.jvm.basics.zookeeper** | ZooKeeper：curator、raw API |

## 运行方式

- **普通 main**：在 IDE 中运行对应包下的 `Main` 或各示例类。
- **Servlet 示例**：本模块打包为 war，可部署到 Tomcat 等容器，或使用内嵌 Tomcat 运行。

## 编译与打包

在 **jvm/java-basics** 目录下执行：

```bash
mvn compile
# 打 war（含 Servlet 与 CXF 等）
mvn package
```

## 来源说明

由原 12 个子模块合并而来；算法模块源码也已并入本项目。包名与逻辑保持不变，仅进行工程归并（packaging war 以支持 servlet webapp）。
