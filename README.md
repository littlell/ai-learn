# 技术学习训练场

本目录聚焦于**通过代码实践想法、加深概念理解**：写可运行的 Demo、做小实验、刷题、验证技术点。架构设计、思路梳理与绘图类内容在别处学习和沉淀，这里只做「能跑起来的代码」练习。

## 使用方式

- **按主题分类**：每个子目录对应一类技术关注点，便于按主题做 Demo、做 POC、巩固基础。
- **可随时扩展**：需要新分类时，在根目录下新增子目录即可，并可在本 README 中补充说明。
- **与工作解耦**：此处仅用于学习与实验，不依赖具体业务项目。

## 统一构建（Maven 聚合入口）

JVM 技术栈统一构建入口位于 `jvm/pom.xml`，在 `jvm` 目录下执行：

```bash
mvn compile
```

## 目录结构

| 目录 | 用途简述 |
|------|----------|
| [jvm](./jvm) | JVM 代码主目录：Java 基础、算法实现、Spring 后端框架等 |
| [database](./database) | 数据库与存储：SQL、NoSQL、缓存、事务与调优 |
| [distributed](./distributed) | 分布式与中间件：消息队列、一致性、分布式事务等 |
| [network](./network) | 计算机网络：Socket、HTTP/TCP 行为、协议验证、抓包与解析等 |
| [cloud-native](./cloud-native) | 云原生与运维：Docker、K8s、CI/CD、可观测性 |
| [linux](./linux) | Linux：常用命令、Shell 脚本、进程与文件、文本处理与排查等 |
| [other-languages](./other-languages) | 其他语言学习：Go、Python、Rust 等，可按语言再分子目录 |
| [books](./books) | 读书学习：按书名为子目录，通过源码跟读技术类书籍（含凤凰架构示例） |