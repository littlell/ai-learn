package com.demo.jvm.spring.boot.kafka.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka配置类，用于配置Kafka主题和组件
 */
@Component
public class KafkaConfig {
    
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    
    /**
     * 定义测试主题名称
     */
    public static final String TEST_TOPIC = "topic.test";
    
    /**
     * 定义JSON测试主题名称
     */
    public static final String JSON_TOPIC = "topic.json";
    
    /**
     * 消费者组ID
     */
    public static final String GROUP_ID = "group.test";
    
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
     * 字符串消费者工厂
     * @return ConsumerFactory实例
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
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
}