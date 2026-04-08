package com.demo.jvm.spring.boot.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 主题交换机消息生产者
 */
@Component
public class TopicMessageProducer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Value("${mq.config.topic.exchange}")
  private String topicExchange;

  /**
   * 发送订单创建消息
   */
  public void sendOrderCreateMessage(String orderId) {
    String routingKey = "order.create";
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String message = String.format("[ORDER_CREATE][%s] 订单创建: %s", timestamp, orderId);
    
    rabbitTemplate.convertAndSend(topicExchange, routingKey, message);
    System.out.println("发送订单创建消息: " + message);
    System.out.println("路由键: " + routingKey + " (将匹配 order.* 和 # 模式)");
  }

  /**
   * 发送订单取消消息
   */
  public void sendOrderCancelMessage(String orderId) {
    String routingKey = "order.cancel";
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String message = String.format("[ORDER_CANCEL][%s] 订单取消: %s", timestamp, orderId);
    
    rabbitTemplate.convertAndSend(topicExchange, routingKey, message);
    System.out.println("发送订单取消消息: " + message);
    System.out.println("路由键: " + routingKey + " (将匹配 order.* 和 # 模式)");
  }

  /**
   * 发送用户登录消息
   */
  public void sendUserLoginMessage(String username) {
    String routingKey = "user.login";
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String message = String.format("[USER_LOGIN][%s] 用户登录: %s", timestamp, username);
    
    rabbitTemplate.convertAndSend(topicExchange, routingKey, message);
    System.out.println("发送用户登录消息: " + message);
    System.out.println("路由键: " + routingKey + " (将匹配 user.* 和 # 模式)");
  }

  /**
   * 发送用户登出消息
   */
  public void sendUserLogoutMessage(String username) {
    String routingKey = "user.logout";
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String message = String.format("[USER_LOGOUT][%s] 用户登出: %s", timestamp, username);
    
    rabbitTemplate.convertAndSend(topicExchange, routingKey, message);
    System.out.println("发送用户登出消息: " + message);
    System.out.println("路由键: " + routingKey + " (将匹配 user.* 和 # 模式)");
  }
}
