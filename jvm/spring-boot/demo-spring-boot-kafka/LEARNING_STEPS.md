# Kafka 增量式学习步骤

## 学习目标
通过增量式学习，逐步掌握 Kafka 的核心概念和使用方法，每次只专注于一个功能点，便于理解和实践。

## 步骤规划

### 步骤 1：环境配置与基础依赖
**学习目标**：搭建 Kafka 开发环境，配置基础依赖

**Kafka 配置**：
1. 安装并启动本地 Kafka 和 Zookeeper
2. 验证 Kafka 服务是否正常运行

**代码修改**：
1. 确保 `pom.xml` 中包含 Spring Kafka 依赖
2. 配置 `application.yml` 连接本地 Kafka 服务器

**测试方法**：
- 运行 `kafka-topics.sh --list --bootstrap-server localhost:9092` 查看主题列表

### 步骤 2：创建第一个 Kafka 主题
**学习目标**：了解 Kafka 主题概念，实现主题自动创建

**Kafka 配置**：
- 在 `application.yml` 中添加 Kafka Admin 配置

**代码修改**：
1. 修改 `KafkaConfig.java`，添加 `KafkaAdmin` Bean
2. 创建 `NewTopic` Bean，定义测试主题

**测试方法**：
- 启动应用，查看日志确认主题是否创建成功
- 使用 `kafka-topics.sh --describe --topic topic.test --bootstrap-server localhost:9092` 查看主题详情

### 步骤 3：实现简单生产者
**学习目标**：了解 Kafka 生产者概念，实现消息发送

**Kafka 配置**：
- 无需额外配置

**代码修改**：
1. 在 `KafkaConfig.java` 中配置生产者工厂
2. 创建 `KafkaTemplate` Bean
3. 编写生产者测试类 `ProducerTest.java`

**测试方法**：
- 运行测试类，发送测试消息
- 使用 `kafka-console-consumer.sh --topic topic.test --bootstrap-server localhost:9092 --from-beginning` 接收消息

### 步骤 4：实现简单消费者
**学习目标**：了解 Kafka 消费者概念，实现消息接收

**Kafka 配置**：
- 无需额外配置

**代码修改**：
1. 在 `KafkaConfig.java` 中配置消费者工厂
2. 创建 `ConcurrentKafkaListenerContainerFactory` Bean
3. 编写 `KafkaConsumer.java`，添加 `@KafkaListener` 注解

**测试方法**：
- 启动应用
- 运行生产者测试，查看消费者是否接收到消息

### 步骤 5：JSON 序列化与反序列化
**学习目标**：了解 Kafka 序列化机制，实现 JSON 对象的发送和接收

**Kafka 配置**：
- 无需额外配置

**代码修改**：
1. 创建 `User.java` 实体类
2. 配置 JSON 序列化的生产者工厂
3. 配置 JSON 反序列化的消费者工厂
4. 添加发送和接收 User 对象的代码

**测试方法**：
- 运行测试类发送 User 对象
- 查看消费者是否成功接收并解析 JSON 对象

### 步骤 6：消费者组管理
**学习目标**：了解 Kafka 消费者组概念，实现多组消费

**Kafka 配置**：
- 无需额外配置

**代码修改**：
1. 为同一主题添加多个消费者组
2. 实现不同消费者组的差异化处理逻辑

**测试方法**：
- 发送消息，查看不同消费者组是否都能接收到消息
- 观察不同消费者组的处理结果

### 步骤 7：Kafka 事务支持
**学习目标**：了解 Kafka 事务概念，实现事务性消息发送

**Kafka 配置**：
- 在 `application.yml` 中添加事务相关配置

**代码修改**：
1. 配置事务性生产者工厂
2. 添加 `KafkaTransactionManager` Bean
3. 实现事务性消息发送代码

**测试方法**：
- 运行测试类发送多条事务性消息
- 验证消息是否全部成功或全部失败

## 学习建议

1. **循序渐进**：每次只完成一个步骤，确保理解后再进行下一步
2. **实践优先**：每个步骤都要实际运行代码，观察结果
3. **查阅文档**：遇到问题时，查阅官方文档和相关资料
4. **调试日志**：启用 Kafka 日志，观察内部运行机制
5. **对比学习**：对比不同配置下的运行结果，理解配置的作用

## 每个步骤的详细实现

### 步骤 1：环境配置与基础依赖

**pom.xml 依赖配置**：
```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

**application.yml 配置**：
```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
```

### 步骤 2：创建第一个 Kafka 主题

**application.yml 配置**：
```yaml
spring:
  kafka:
    admin:
      properties:
        bootstrap.servers: localhost:9092
```

**KafkaConfig.java 修改**：
```java
/**
 * Kafka Admin配置，用于自动创建主题
 * @return KafkaAdmin实例
 */
@Bean
public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return new KafkaAdmin(configs);
}

/**
 * 创建测试主题
 * - 1个分区
 * - 1个副本
 * @return NewTopic实例
 */
@Bean
public NewTopic testTopic() {
    return TopicBuilder.name(TEST_TOPIC)
            .partitions(1)  // 分区数
            .replicas(1)     // 副本数
            .build();
}

/**
 * 创建JSON测试主题
 * - 2个分区
 * - 1个副本
 * @return NewTopic实例
 */
@Bean
public NewTopic jsonTopic() {
    return TopicBuilder.name(JSON_TOPIC)
            .partitions(2)  // 分区数
            .replicas(1)     // 副本数
            .build();
}
```

### 步骤 3：实现简单生产者

**KafkaConfig.java 修改**：
```java
/**
 * 字符串生产者配置
 * @return 生产者配置映射
 */
@Bean
public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    // Kafka服务器地址
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    // 键序列化器
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    // 值序列化器
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return props;
}

/**
 * 字符串生产者工厂
 * @return ProducerFactory实例
 */
@Bean
public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
}

/**
 * 字符串Kafka模板，用于发送字符串消息
 * @return KafkaTemplate实例
 */
@Bean
public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
}
```

**ProducerTest.java 创建**：
```java
@SpringBootTest
public class ProducerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void send() {
        kafkaTemplate.send(KafkaConfig.TEST_TOPIC, "hello kafka");
    }
}
```

### 步骤 4：实现简单消费者

**KafkaConfig.java 修改**：
```java
/**
 * 字符串消费者配置
 * @return 消费者配置映射
 */
@Bean
public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    // Kafka服务器地址
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    // 消费者组ID
    props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
    // 自动偏移量重置策略：earliest-从最早消息开始消费，latest-从最新消息开始消费
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    // 是否自动提交偏移量
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
    // 自动提交偏移量的时间间隔（毫秒）
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
    // 键反序列化器
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    // 值反序列化器
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    return props;
}

/**
 * 字符串消费者工厂
 * @return ConsumerFactory实例
 */
@Bean
public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
}

/**
 * 字符串监听器容器工厂，用于创建字符串消费者容器
 * @return ConcurrentKafkaListenerContainerFactory实例
 */
@Bean
public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    // 设置并发消费者数量，应小于等于分区数
    factory.setConcurrency(1);
    return factory;
}
```

**KafkaConsumer.java 创建**：
```java
/**
 * Kafka消费者类，用于接收和处理Kafka消息
 */
@Component
public class KafkaConsumer {
    
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    
    /**
     * 监听字符串测试主题，接收消息（消费者组1）
     * 
     * @param message 接收到的消息内容
     */
    @KafkaListener(topics = KafkaConfig.TEST_TOPIC, groupId = KafkaConfig.GROUP_ID)
    public void receiveMessage(String message) {
        logger.info("[Group 1] Received string message from topic '{}': {}", KafkaConfig.TEST_TOPIC, message);
        System.out.println("[Group 1] Received string message: " + message);
        // 在这里可以添加消息处理逻辑
        processMessage(message);
    }
    
    /**
     * 字符串消息处理方法（消费者组1）
     * 
     * @param message 要处理的消息
     */
    private void processMessage(String message) {
        // 简单的消息处理逻辑示例
        logger.info("[Group 1] Processing string message: {}", message);
        
        // 模拟业务处理
        try {
            // 这里可以添加实际的业务逻辑
            Thread.sleep(500); // 模拟处理延迟
            logger.info("[Group 1] String message processed successfully");
        } catch (InterruptedException e) {
            logger.error("[Group 1] Error processing string message: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
```

### 步骤 5：JSON 序列化与反序列化

**User.java 创建**：
```java
public class User implements Serializable {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    // Getters and Setters
}
```

**KafkaConfig.java 修改**：
```java
/**
 * JSON生产者配置
 * @return 生产者配置映射
 */
@Bean
public Map<String, Object> jsonProducerConfigs() {
    Map<String, Object> props = new HashMap<>();
    // Kafka服务器地址
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    // 键序列化器
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    // 值序列化器：使用JSON序列化
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return props;
}

/**
 * JSON生产者工厂
 * @return ProducerFactory实例
 */
@Bean
public ProducerFactory<String, User> jsonProducerFactory() {
    return new DefaultKafkaProducerFactory<>(jsonProducerConfigs());
}

/**
 * JSON Kafka模板，用于发送JSON对象消息
 * @return KafkaTemplate实例
 */
@Bean
public KafkaTemplate<String, User> jsonKafkaTemplate() {
    return new KafkaTemplate<>(jsonProducerFactory());
}

/**
 * JSON消费者配置
 * @return 消费者配置映射
 */
@Bean
public Map<String, Object> jsonConsumerConfigs() {
    Map<String, Object> props = new HashMap<>(consumerConfigs());
    // 值反序列化器：使用JSON反序列化
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    return props;
}

/**
 * JSON消费者工厂
 * @return ConsumerFactory实例
 */
@Bean
public ConsumerFactory<String, User> jsonConsumerFactory() {
    JsonDeserializer<User> jsonDeserializer = new JsonDeserializer<>(User.class);
    // 信任的包，允许反序列化所有包下的对象
    jsonDeserializer.addTrustedPackages("*");
    
    return new DefaultKafkaConsumerFactory<>(
            jsonConsumerConfigs(),
            new StringDeserializer(),
            jsonDeserializer
    );
}

/**
 * JSON监听器容器工厂，用于创建JSON消费者容器
 * @return ConcurrentKafkaListenerContainerFactory实例
 */
@Bean
public ConcurrentKafkaListenerContainerFactory<String, User> jsonKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(jsonConsumerFactory());
    // 设置并发消费者数量，应小于等于分区数
    factory.setConcurrency(2);
    return factory;
}
```

### 步骤 6：消费者组管理

**KafkaConsumer.java 修改**：
```java
/**
 * 监听字符串测试主题，接收消息（消费者组1）
 * 
 * @param message 接收到的消息内容
 */
@KafkaListener(topics = KafkaConfig.TEST_TOPIC, groupId = KafkaConfig.GROUP_ID)
public void receiveMessage(String message) {
    logger.info("[Group 1] Received string message from topic '{}': {}", KafkaConfig.TEST_TOPIC, message);
    System.out.println("[Group 1] Received string message: " + message);
    // 在这里可以添加消息处理逻辑
    processMessage(message);
}

/**
 * 监听字符串测试主题，接收消息（消费者组2）
 * 
 * @param message 接收到的消息内容
 */
@KafkaListener(topics = KafkaConfig.TEST_TOPIC, groupId = "group.test.2")
public void receiveMessageGroup2(String message) {
    logger.info("[Group 2] Received string message from topic '{}': {}", KafkaConfig.TEST_TOPIC, message);
    System.out.println("[Group 2] Received string message: " + message);
    // 差异化处理逻辑：将消息转换为大写
    processMessageGroup2(message);
}

/**
 * 监听JSON测试主题，接收User对象消息
 * 使用jsonKafkaListenerContainerFactory来处理JSON反序列化
 * 
 * @param user 接收到的User对象
 */
@KafkaListener(
        topics = KafkaConfig.JSON_TOPIC, 
        groupId = KafkaConfig.GROUP_ID,
        containerFactory = "jsonKafkaListenerContainerFactory")
public void receiveUserMessage(User user) {
    logger.info("Received User object from topic '{}': {}", KafkaConfig.JSON_TOPIC, user);
    System.out.println("Received User object: " + user);
    // 在这里可以添加User对象处理逻辑
    processUserMessage(user);
}

// 消息处理方法示例
private void processMessage(String message) {
    // 简单的消息处理逻辑
    logger.info("[Group 1] Processing string message: {}", message);
}

private void processMessageGroup2(String message) {
    // 差异化处理：将消息转换为大写
    String upperCaseMessage = message.toUpperCase();
    logger.info("[Group 2] Processing string message (converted to uppercase): {}", upperCaseMessage);
}

private void processUserMessage(User user) {
    // 简单的User对象处理逻辑
    logger.info("Processing User object: {}", user);
}
```

### 步骤 7：Kafka 事务支持

**KafkaConfig.java 修改**：
```java
/**
 * 事务性生产者配置
 * @return 生产者配置映射
 */
@Bean
public Map<String, Object> transactionalProducerConfigs() {
    Map<String, Object> props = new HashMap<>(jsonProducerConfigs());
    // 事务ID，用于标识事务生产者
    props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactional-producer-1");
    // 启用幂等性
    props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
    // 确认模式：all表示所有副本确认
    props.put(ProducerConfig.ACKS_CONFIG, "all");
    return props;
}

/**
 * 事务性生产者工厂
 * @return ProducerFactory实例
 */
@Bean
public ProducerFactory<String, User> transactionalProducerFactory() {
    return new DefaultKafkaProducerFactory<>(transactionalProducerConfigs());
}

/**
 * Kafka事务管理器
 * @return KafkaTransactionManager实例
 */
@Bean
public KafkaTransactionManager<String, User> kafkaTransactionManager() {
    return new KafkaTransactionManager<>(transactionalProducerFactory());
}

/**
 * 事务性Kafka模板，用于发送事务性消息
 * @return KafkaTemplate实例
 */
@Bean
public KafkaTemplate<String, User> transactionalKafkaTemplate() {
    KafkaTemplate<String, User> template = new KafkaTemplate<>(transactionalProducerFactory());
    // 设置事务ID前缀
    template.setTransactionIdPrefix("transactional-");
    return template;
}
```

## 总结

通过以上 7 个增量式学习步骤，你将逐步掌握 Kafka 的核心概念和使用方法。每个步骤都专注于一个功能点，便于理解和实践。建议你按照顺序完成每个步骤，并在实际运行中观察结果，加深理解。

在学习过程中，你可以随时查阅 Kafka 官方文档和 Spring Kafka 文档，获取更详细的信息。同时，结合日志分析和监控工具，深入理解 Kafka 的运行机制。