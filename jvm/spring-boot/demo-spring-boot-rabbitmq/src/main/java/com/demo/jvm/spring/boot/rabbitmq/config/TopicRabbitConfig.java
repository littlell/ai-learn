package com.demo.jvm.spring.boot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题交换机配置类
 */
@Configuration
public class TopicRabbitConfig {

  @Value("${mq.config.topic.exchange}")
  private String topicExchange;

  @Value("${mq.config.topic.queue.order}")
  private String orderQueue;

  @Value("${mq.config.topic.queue.user}")
  private String userQueue;

  @Value("${mq.config.topic.queue.all}")
  private String allQueue;

  /**
   * 创建主题交换机
   */
  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(topicExchange, true, false);
  }

  /**
   * 创建订单队列
   */
  @Bean
  public Queue orderQueue() {
    return QueueBuilder.durable(orderQueue).build();
  }

  /**
   * 创建用户队列
   */
  @Bean
  public Queue userQueue() {
    return QueueBuilder.durable(userQueue).build();
  }

  /**
   * 创建全部消息队列
   */
  @Bean
  public Queue allQueue() {
    return QueueBuilder.durable(allQueue).build();
  }

  /**
   * 将订单队列绑定到主题交换机
   * 路由键模式: order.* (匹配所有以order.开头的路由键)
   */
  @Bean
  public Binding orderBinding() {
    return BindingBuilder.bind(orderQueue()).to(topicExchange()).with("order.*");
  }

  /**
   * 将用户队列绑定到主题交换机
   * 路由键模式: user.* (匹配所有以user.开头的路由键)
   */
  @Bean
  public Binding userBinding() {
    return BindingBuilder.bind(userQueue()).to(topicExchange()).with("user.*");
  }

  /**
   * 将全部消息队列绑定到主题交换机
   * 路由键模式: # (匹配所有路由键)
   */
  @Bean
  public Binding allBinding() {
    return BindingBuilder.bind(allQueue()).to(topicExchange()).with("#");
  }
}
