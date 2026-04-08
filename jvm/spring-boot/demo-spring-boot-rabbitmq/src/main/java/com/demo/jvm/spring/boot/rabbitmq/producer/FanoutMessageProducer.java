package com.demo.jvm.spring.boot.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 扇出交换机消息生产者
 */
@Component
public class FanoutMessageProducer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Value("${mq.config.fanout.exchange}")
  private String fanoutExchange;

  /**
   * 发送广播消息
   * 扇出交换机会将消息发送到所有绑定的队列，忽略路由键
   */
  public void sendBroadcastMessage(String message) {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String fullMessage = String.format("[BROADCAST][%s] %s", timestamp, message);
    
    // 扇出交换机不需要路由键，可以传入空字符串或任意字符串
    rabbitTemplate.convertAndSend(fanoutExchange, "", fullMessage);
    System.out.println("发送广播消息: " + fullMessage);
    System.out.println("该消息将被发送到所有绑定的队列: 邮件队列、短信队列、推送队列");
  }
}
