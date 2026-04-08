# Kafka Demo with Spring Boot

## 1. 项目概述

本项目是一个基于Spring Boot的Kafka学习示例，演示了Kafka的核心功能和最佳实践。通过这个项目，您可以学习Kafka的基础概念、生产者消费者实现、序列化方式、分区策略、消费者组管理以及事务可靠性等高级特性。

## 2. 核心功能实现

### 2.1 基础配置
- **本地Kafka环境配置**：在`application.yml`中配置了本地Kafka服务器连接
- **Topic自动创建**：通过`KafkaAdmin`和`NewTopic`实现主题的自动创建
- **多种主题配置**：
  - `topic.test`：1个分区，1个副本
  - `topic.json`：2个分区，1个副本
  - `topic.partitioned`：3个分区，1个副本

### 2.2 生产者功能
- **HTTP接口发送消息**：提供了RESTful API用于发送Kafka消息
- **多种序列化方式**：
  - 字符串序列化
  - JSON对象序列化
- **自定义分区器**：基于年龄的分区策略，将不同年龄段的用户分配到不同分区
- **事务支持**：实现了Kafka事务，确保多条消息的原子性发送

### 2.3 消费者功能
- **多消费者组**：演示了同一主题可以被多个消费者组独立消费
- **消息处理**：实现了不同类型消息的处理逻辑
- **错误处理**：添加了基本的错误处理机制
- **分区消费**：演示了特定分区的消费方式

## 3. API接口列表

### 3.1 发送简单字符串消息
```
POST /kafka/send
参数：
- topic：主题名称（可选，默认：topic.test）
- message：消息内容（必填）
- key：分区键（可选）
```

### 3.2 发送JSON格式消息
```
POST /kafka/send-json
请求体：
{
  "topic": "topic.json",
  "message": "{\"name\": \"test\"}",
  "key": "test-key"
}
```

### 3.3 发送User对象消息
```
POST /kafka/send-user
请求体：
{
  "topic": "topic.json",
  "user": {
    "id": 1,
    "name": "张三",
    "email": "zhangsan@example.com",
    "age": 25
  }
}
```

### 3.4 使用自定义分区器发送User对象
```
POST /kafka/send-user-custom-partitioner
请求体：
{
  "topic": "topic.partitioned",
  "user": {
    "id": 2,
    "name": "李四",
    "email": "lisi@example.com",
    "age": 30
  }
}
```

### 3.5 事务性发送多条消息
```
POST /kafka/send-user-transactional
请求体：
{
  "requests": [
    {
      "user": {
        "id": 3,
        "name": "王五",
        "email": "wangwu@example.com",
        "age": 15
      }
    },
    {
      "user": {
        "id": 4,
        "name": "赵六",
        "email": "zhaoliu@example.com",
        "age": 65
      }
    }
  ]
}
```

## 4. Kafka监控

### 4.1 内置监控指标
Kafka提供了丰富的JMX指标，包括：
- **生产者指标**：消息发送速率、成功率、延迟等
- **消费者指标**：消息消费速率、积压量、偏移量等
- **Broker指标**：请求速率、吞吐量、分区状态等

### 4.2 监控工具
- **JConsole/JVisualVM**：通过JMX连接Kafka进程查看指标
- **Prometheus + Grafana**：推荐的监控方案，提供丰富的Dashboard
- **Kafka Manager**：Yahoo开源的Kafka集群管理工具
- **Confluent Control Center**：商业监控工具，功能全面

### 4.3 关键监控指标
- **生产者**：
  - `producer-metrics:record-send-rate`：消息发送速率
  - `producer-metrics:record-error-rate`：消息发送错误率
  - `producer-metrics:request-latency-avg`：请求平均延迟

- **消费者**：
  - `consumer-metrics:records-consumed-rate`：消息消费速率
  - `consumer-metrics:records-lag`：消息积压量
  - `consumer-metrics:commit-latency-avg`：偏移量提交平均延迟

- **Broker**：
  - `broker-metrics:messages-in-per-sec`：每秒接收消息数
  - `broker-metrics:bytes-in-per-sec`：每秒接收字节数
  - `broker-metrics:under-replicated-partitions`：副本同步落后的分区数

## 5. Kafka最佳实践

### 5.1 主题设计
- **分区数量**：根据吞吐量需求和消费者数量合理设置，一般建议每个分区的吞吐量为10MB/s
- **副本数量**：建议设置为3个副本，提供足够的可靠性
- **主题命名规范**：使用点分隔的命名方式，如`business.domain.topic`

### 5.2 生产者最佳实践
- **确认机制**：根据可靠性要求选择合适的acks参数
  - `acks=0`：最高吞吐量，无可靠性保障
  - `acks=1`：中等可靠性，仅leader确认
  - `acks=all`：最高可靠性，所有ISR确认
- **批量发送**：开启批量发送提高吞吐量
- **幂等性**：开启幂等性防止消息重复发送
- **事务**：对于关键业务，使用事务确保消息的原子性

### 5.3 消费者最佳实践
- **消费者组管理**：合理设置消费者组，避免单个组消费者过多
- **并发消费**：消费者数量应小于等于分区数量
- **偏移量管理**：根据业务需求选择自动提交或手动提交
- **消息处理**：快速处理消息，避免长时间阻塞消费者

### 5.4 性能优化
- **序列化选择**：根据业务需求选择合适的序列化方式
  - 字符串序列化：简单高效
  - JSON序列化：灵活通用
  - Avro序列化：紧凑高效，支持Schema演进
- **压缩配置**：开启消息压缩，减少网络传输和存储开销
  - 推荐使用LZ4或ZSTD压缩算法
- **缓冲区设置**：合理设置生产者和消费者的缓冲区大小

### 5.5 可靠性保障
- **副本配置**：确保足够的副本数量
- **ISR管理**：合理配置`min.insync.replicas`参数
- **事务支持**：关键业务使用事务确保Exactly-once语义
- **监控告警**：设置合理的监控告警阈值

## 6. 运行与测试

### 6.1 环境要求
- JDK 1.8+ 
- Maven 3.6+ 
- Kafka 2.0+ 
- Zookeeper 3.5+ 

### 6.2 启动步骤
1. 启动Zookeeper：`bin/zookeeper-server-start.sh config/zookeeper.properties`
2. 启动Kafka：`bin/kafka-server-start.sh config/server.properties`
3. 编译项目：`mvn clean package`
4. 运行应用：`mvn spring-boot:run`

### 6.3 测试方法
- 使用Postman或curl调用API接口发送消息
- 查看应用日志，观察消费者的消息接收情况
- 使用Kafka命令行工具查看主题和消息：
  - 查看主题列表：`bin/kafka-topics.sh --list --bootstrap-server localhost:9092`
  - 消费消息：`bin/kafka-console-consumer.sh --topic topic.test --bootstrap-server localhost:9092 --from-beginning`

## 7. 学习资源推荐

1. **官方文档**：[Apache Kafka Documentation](https://kafka.apache.org/documentation/)
2. **Spring Kafka文档**：[Spring for Apache Kafka](https://docs.spring.io/spring-kafka/docs/current/reference/html/)
3. **书籍**：《Kafka权威指南》
4. **在线课程**：Confluent Developer Courses
5. **社区资源**：Kafka Summit会议视频、GitHub示例项目

## 8. 项目结构

```
src/main/java/com/demo/jvm/spring/boot/boot07/
├── kafka/
│   ├── AgeBasedPartitioner.java      # 自定义分区器
│   ├── KafkaConfig.java              # Kafka配置类
│   ├── KafkaConsumer.java            # 消费者实现
│   ├── KafkaProducerController.java  # 生产者REST API
│   └── User.java                     # 测试实体类
└── Boot07Main.java                   # 应用入口
```

## 9. 总结

通过这个项目，您可以系统地学习Kafka的核心概念和最佳实践。从基础的生产者消费者实现，到高级的分区策略、事务支持和监控管理，涵盖了Kafka的主要使用场景。

建议您结合官方文档和实际测试，深入理解Kafka的工作原理和设计理念，以便在实际项目中更好地应用Kafka。
