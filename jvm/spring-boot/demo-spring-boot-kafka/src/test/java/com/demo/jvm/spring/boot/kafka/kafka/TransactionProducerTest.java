package com.demo.jvm.spring.boot.kafka.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * Kafka事务生产者测试类，用于测试事务性消息发送功能
 */
@SpringBootTest
public class TransactionProducerTest {
    
    @Autowired
    private KafkaTemplate<String, User> transactionalKafkaTemplate;
    
    /**
     * 测试事务性发送多条User对象消息
     */
    @Test
    public void sendTransactionalMessages() {
        // 创建多个User对象
        User user1 = new User();
        user1.setId(100L);
        user1.setName("事务用户1");
        user1.setEmail("transaction1@example.com");
        user1.setAge(30);
        
        User user2 = new User();
        user2.setId(101L);
        user2.setName("事务用户2");
        user2.setEmail("transaction2@example.com");
        user2.setAge(31);
        
        User user3 = new User();
        user3.setId(102L);
        user3.setName("事务用户3");
        user3.setEmail("transaction3@example.com");
        user3.setAge(32);
        
        System.out.println("Starting transaction...");
        
        // 使用executeInTransaction方法执行事务性操作
        transactionalKafkaTemplate.executeInTransaction(template -> {
            try {
                // 在同一个事务中发送多条消息
                template.send(KafkaConfig.JSON_TOPIC, user1.getId().toString(), user1);
                System.out.println("Sent User 1 in transaction: " + user1);
                
                template.send(KafkaConfig.JSON_TOPIC, user2.getId().toString(), user2);
                System.out.println("Sent User 2 in transaction: " + user2);
                
                template.send(KafkaConfig.JSON_TOPIC, user3.getId().toString(), user3);
                System.out.println("Sent User 3 in transaction: " + user3);
                
                System.out.println("Transaction completed successfully!");
                return true;
            } catch (Exception e) {
                System.out.println("Transaction failed: " + e.getMessage());
                throw e; // 触发事务回滚
            }
        });
        
        // 等待一下，确保事务提交和消息发送完成
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 测试事务回滚功能
     */
    @Test
    public void testTransactionRollback() {
        // 创建User对象
        User user1 = new User();
        user1.setId(200L);
        user1.setName("回滚测试用户1");
        user1.setEmail("rollback1@example.com");
        user1.setAge(40);
        
        User user2 = new User();
        user2.setId(201L);
        user2.setName("回滚测试用户2");
        user2.setEmail("rollback2@example.com");
        user2.setAge(41);
        
        System.out.println("Starting transaction with rollback...");
        
        try {
            // 使用executeInTransaction方法执行事务性操作
            transactionalKafkaTemplate.executeInTransaction(template -> {
                // 发送第一条消息
                template.send(KafkaConfig.JSON_TOPIC, user1.getId().toString(), user1);
                System.out.println("Sent User 1 in transaction: " + user1);
                
                // 发送第二条消息
                template.send(KafkaConfig.JSON_TOPIC, user2.getId().toString(), user2);
                System.out.println("Sent User 2 in transaction: " + user2);
                
                // 模拟业务逻辑出错，触发回滚
                throw new RuntimeException("Simulating business error to trigger rollback");
            });
        } catch (Exception e) {
            System.out.println("Transaction rolled back due to error: " + e.getMessage());
            // 异常会被捕获，事务已回滚
        }
        
        // 等待一下，确保事务回滚完成
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}