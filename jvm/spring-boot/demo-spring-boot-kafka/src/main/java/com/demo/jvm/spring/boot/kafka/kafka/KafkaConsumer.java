package com.demo.jvm.spring.boot.kafka.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

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
    
    /**
     * 消费者组2的字符串消息处理方法（差异化处理：转换为大写）
     * 
     * @param message 要处理的消息
     */
    private void processMessageGroup2(String message) {
        // 差异化处理：将消息转换为大写
        String upperCaseMessage = message.toUpperCase();
        logger.info("[Group 2] Processing string message (converted to uppercase): {}", upperCaseMessage);
        
        // 模拟业务处理
        try {
            // 这里可以添加实际的业务逻辑
            Thread.sleep(500); // 模拟处理延迟
            logger.info("[Group 2] String message processed successfully with uppercase conversion");
        } catch (InterruptedException e) {
            logger.error("[Group 2] Error processing string message: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * User对象处理方法
     * 
     * @param user 要处理的User对象
     */
    private void processUserMessage(User user) {
        // 简单的User对象处理逻辑示例
        logger.info("Processing User object: {}", user);
        
        // 模拟业务处理
        try {
            // 这里可以添加实际的业务逻辑，例如保存到数据库
            logger.info("Processing user: {} (ID: {})", user.getName(), user.getId());
            Thread.sleep(500); // 模拟处理延迟
            logger.info("User object processed successfully");
        } catch (InterruptedException e) {
            logger.error("Error processing User object: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}