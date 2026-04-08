package com.demo.jvm.spring.boot.kafka.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Kafka JSON生产者测试类，用于测试User对象的JSON序列化和发送功能
 */
@SpringBootTest
public class UserProducerTest {
    
    @Autowired
    private KafkaTemplate<String, User> jsonKafkaTemplate;
    
    /**
     * 测试发送User对象到JSON主题
     */
    @Test
    public void sendUserMessage() {
        // 创建一个User对象
        User user = new User();
        user.setId(1L);
        user.setName("张三");
        user.setEmail("zhangsan@example.com");
        user.setAge(25);
        
        System.out.println("Sending User object: " + user);
        
        // 发送User对象到JSON主题
        // 第一个参数：主题名称
        // 第二个参数：消息键（使用用户ID作为键）
        // 第三个参数：User对象
        jsonKafkaTemplate.send(KafkaConfig.JSON_TOPIC, user.getId().toString(), user);
        
        System.out.println("User object sent successfully!");
        
        // 等待一下，确保消息发送完成
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}