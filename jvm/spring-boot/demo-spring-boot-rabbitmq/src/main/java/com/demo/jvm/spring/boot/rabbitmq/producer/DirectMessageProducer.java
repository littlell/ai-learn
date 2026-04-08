package com.demo.jvm.spring.boot.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 直连交换机消息生产者
 */
@Component
public class DirectMessageProducer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Value("${mq.config.direct.exchange}")
  private String directExchange;

  @Value("${mq.config.direct.routing-key.info}")
  private String infoRoutingKey;

  @Value("${mq.config.direct.routing-key.warning}")
  private String warningRoutingKey;

  @Value("${mq.config.direct.routing-key.error}")
  private String errorRoutingKey;

  /**
   * 发送info级别消息
   */
  public void sendInfoMessage(String message) {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String fullMessage = String.format("[INFO][%s] %s", timestamp, message);
    
    rabbitTemplate.convertAndSend(directExchange, infoRoutingKey, fullMessage);
    System.out.println("发送INFO消息: " + fullMessage);
  }

  /**
   * 发送warning级别消息
   */
  public void sendWarningMessage(String message) {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String fullMessage = String.format("[WARNING][%s] %s", timestamp, message);
    
    rabbitTemplate.convertAndSend(directExchange, warningRoutingKey, fullMessage);
    System.out.println("发送WARNING消息: " + fullMessage);
  }

  /**
   * 发送error级别消息
   */
  public void sendErrorMessage(String message) {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String fullMessage = String.format("[ERROR][%s] %s", timestamp, message);
    
    rabbitTemplate.convertAndSend(directExchange, errorRoutingKey, fullMessage);
    System.out.println("发送ERROR消息: " + fullMessage);
  }
}
