package com.demo.jvm.spring.boot.kafka.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Kafka生产者测试类，用于测试消息发送功能
 */
@SpringBootTest
public class ProducerTest {
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    /**
     * 测试发送简单字符串消息到测试主题
     */
    @Test
    public void sendMessage() {
        // 定义消息内容
        String message = "Hello, Kafka! This is a test message.";
        
        System.out.println("Sending message: " + message);
        
        // 发送消息到指定主题
        // 第一个参数：主题名称
        // 第二个参数：消息内容
        kafkaTemplate.send(KafkaConfig.TEST_TOPIC, message);
        
        System.out.println("Message sent successfully!");
        
        // 等待一下，确保消息发送完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 测试发送带键的消息
     */
    @Test
    public void sendMessageWithKey() {
        // 定义消息键和内容
        String key = "test-key-1";
        String message = "Hello, Kafka! This is a message with key.";
        
        System.out.println("Sending message with key '" + key + "': " + message);
        
        // 发送消息到指定主题，指定键
        // 第一个参数：主题名称
        // 第二个参数：消息键
        // 第三个参数：消息内容
        kafkaTemplate.send(KafkaConfig.TEST_TOPIC, key, message);
        
        System.out.println("Message with key sent successfully!");
        
        // 等待一下，确保消息发送完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}