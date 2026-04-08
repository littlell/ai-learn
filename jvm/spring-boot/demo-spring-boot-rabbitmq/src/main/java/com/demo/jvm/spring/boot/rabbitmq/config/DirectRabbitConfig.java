package com.demo.jvm.spring.boot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连交换机配置类
 */
@Configuration
public class DirectRabbitConfig {

  @Value("${mq.config.direct.exchange}")
  private String directExchange;

  @Value("${mq.config.direct.queue.info}")
  private String infoQueue;

  @Value("${mq.config.direct.queue.warning}")
  private String warningQueue;

  @Value("${mq.config.direct.queue.error}")
  private String errorQueue;

  @Value("${mq.config.direct.routing-key.info}")
  private String infoRoutingKey;

  @Value("${mq.config.direct.routing-key.warning}")
  private String warningRoutingKey;

  @Value("${mq.config.direct.routing-key.error}")
  private String errorRoutingKey;

  /**
   * 创建直连交换机
   */
  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(directExchange, true, false);
  }

  /**
   * 创建info队列
   */
  @Bean
  public Queue infoQueue() {
    return QueueBuilder.durable(infoQueue).build();
  }

  /**
   * 创建warning队列
   */
  @Bean
  public Queue warningQueue() {
    return QueueBuilder.durable(warningQueue).build();
  }

  /**
   * 创建error队列
   */
  @Bean
  public Queue errorQueue() {
    return QueueBuilder.durable(errorQueue).build();
  }

  /**
   * 将info队列绑定到直连交换机
   */
  @Bean
  public Binding infoBinding() {
    return BindingBuilder.bind(infoQueue()).to(directExchange()).with(infoRoutingKey);
  }

  /**
   * 将warning队列绑定到直连交换机
   */
  @Bean
  public Binding warningBinding() {
    return BindingBuilder.bind(warningQueue()).to(directExchange()).with(warningRoutingKey);
  }

  /**
   * 将error队列绑定到直连交换机
   */
  @Bean
  public Binding errorBinding() {
    return BindingBuilder.bind(errorQueue()).to(directExchange()).with(errorRoutingKey);
  }
}
