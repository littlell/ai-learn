# JVM 技术栈实践

放 JVM 相关技术的学习与实践：Java 基础、JVM 运行时、并发、Spring 生态等。

**本目录现有内容：**

- **统一构建入口**：`jvm/pom.xml`，在 `jvm` 目录执行 `mvn compile` 可统一编译。
- **java-basics/**：独立 Maven 工程（与 `jvm/spring-core`、`jvm/spring-boot` 一致），按类型分包。在 **jvm/java-basics** 目录下执行 `mvn compile` 可编译。
- **spring-boot/**：后端框架与微服务相关 Java 工程（Spring Boot）。
- **spring-core/**：Spring Framework 核心学习工程。
- 单文件练习（如 `SecuritySecureRandomObservation.java`）可直接放在本目录下。

JDK 基线统一为 **21**。

可继续添加：JVM/GC 小实验、更多单文件 Demo、Java 新特性试用等。

---

## 模块与包结构说明（是否拆分过细）

### 当前情况

- **模块层面**：lab 下每个「主题」一个 Maven 模块（如 generics、spi、serialize、jackson 等）。其中部分主题**概念简单、类很少**，单独占一个模块会显得碎：
  - 如 **demo-java-spi**（约 4 个类）、**demo-java-jackson**（约 2 个类）、**demo-java-generics**（约 9 个类）、**demo-java-serialize**（约 9 个类）、**demo-java-log**、**demo-java-jna**、**demo-java-maven** 等，都属于「Java 语言/基础概念」类的小 Demo。
- **包层面**：各 demo 内部多为**一个主包 + 少量 bean/util 子包**，没有「一个简单概念一个包」的过度拆分；design-pattern 按模式分包（abstractfactory、singleton 等）是合理的，因为每个模式是一组协作类。

### 已做合并

**jvm** 下已合并为**单一模块** **java-basics**（artifactId: java-basics），按代码类型分包（包名统一为 `com.demo.jvm.basics.*`）：

- `com.demo.jvm.basics.generics/spi/serialize/jackson/jna`
- `com.demo.jvm.basics.apache.cxf`、`com.demo.jvm.basics.bytecode`、`com.demo.jvm.basics.cache`、`com.demo.jvm.basics.concurrency`、`com.demo.jvm.basics.design`、`com.demo.jvm.basics.function`、`com.demo.jvm.basics.jvm`、`com.demo.jvm.basics.rabbitmq`、`com.demo.jvm.basics.reactor`、`com.demo.jvm.basics.servlet`、`com.demo.jvm.basics.zookeeper`

原 12 个子模块已删除，仅保留 **java-basics**（packaging war，含 Servlet webapp）。运行方式见 `java-basics/README.md`。

---

## 其余样例代码是否有类似问题（整体结论）

对 **jvm**、**other-languages** 等目录扫过一遍后的结论如下。

### 1. jvm — 已合并为单模块

jvm 下仅保留 **java-basics**（java-basics）一个模块（按类型分包），与 backend 一样为「主题目录 + 子模块」结构。

### 2. jvm/spring-core — 存在明显「按章节拆成多模块」的过细

- 当前：**23 个子模块**（demo-spring-core01 ～ demo-spring-core23），每个模块只有 2～7 个 Java 类，且多为「跟某一步/某章节」的一小段示例。
- 问题：和「Java 基本概念」合并前类似——**同一主题（Spring Core 学习）被拆成很多小 Maven 工程**，根目录模块过多。
- 建议：合并为**一个**模块 **demo-spring-core**，用**包**区分步骤，例如 `com.demo.jvm.spring.core.core01`、`core02`、…、`core23`，每个包内保留原有类与 `applicationContext.xml`（或统一放到 resources 下按编号）。这样 jvm 下 spring-core 只占一个目录，结构更清晰。

### 3. jvm/spring-boot — 无类似问题

- 每个子项目都是**完整可运行的 Spring Boot 应用**，且技术栈不同（Cassandra、Kafka、MongoDB、RabbitMQ、Resilience4j、RSocket 等），依赖与入口各异。
- **按应用/技术栈分模块是合理的**，不需要合并。

### 4. algorithms（并入 demo）、other-languages — 无类似问题

- **algorithm（in jvm/java-basics）**：包路径为 `com.demo.jvm.basics.algorithm.*`，按主题分包（linklist、queue、recurse、sort、stack），没有「一个概念一个 Maven 模块」。
- **other-languages/python**：`basic/`、`numpy/` 等按主题分目录，文件数量适中，结构清晰。

---

**已做重构（本次）**：

1. **jvm**：已合并为**单模块 java-basics**（artifactId: java-basics，与 backend 一致），按类型分包。
2. **jvm/spring-core**：已合并为单模块并按功能子包（ioc、di、lifecycle、bean、annotation、javaconfig、spel、aop、tx），见 `jvm/spring-core/README.md`。
