package com.demo.jvm.spring.boot.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 主题交换机消息消费者
 */
@Component
public class TopicMessageConsumer {

  /**
   * 监听订单队列消息
   * 绑定模式: order.* (接收所有以order.开头的消息)
   */
  @RabbitListener(queues = "${mq.config.topic.queue.order}")
  public void receiveOrderMessage(String message) {
    System.out.println("===== 订单队列收到消息 =====");
    System.out.println("消息内容: " + message);
    System.out.println("处理订单相关业务逻辑...");
    System.out.println("绑定模式: order.* (匹配 order.create, order.cancel 等)");
    System.out.println("===========================\n");
  }

  /**
   * 监听用户队列消息
   * 绑定模式: user.* (接收所有以user.开头的消息)
   */
  @RabbitListener(queues = "${mq.config.topic.queue.user}")
  public void receiveUserMessage(String message) {
    System.out.println("===== 用户队列收到消息 =====");
    System.out.println("消息内容: " + message);
    System.out.println("处理用户相关业务逻辑...");
    System.out.println("绑定模式: user.* (匹配 user.login, user.logout 等)");
    System.out.println("===========================\n");
  }

  /**
   * 监听全部消息队列
   * 绑定模式: # (接收所有消息，用于日志记录)
   */
  @RabbitListener(queues = "${mq.config.topic.queue.all}")
  public void receiveAllMessage(String message) {
    System.out.println("===== 全部队列收到消息 =====");
    System.out.println("消息内容: " + message);
    System.out.println("记录所有消息到日志系统...");
    System.out.println("绑定模式: # (匹配所有路由键)");
    System.out.println("===========================\n");
  }
}
