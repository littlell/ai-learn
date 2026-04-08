package com.demo.jvm.spring.boot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇出交换机配置类
 */
@Configuration
public class FanoutRabbitConfig {

  @Value("${mq.config.fanout.exchange}")
  private String fanoutExchange;

  @Value("${mq.config.fanout.queue.email}")
  private String emailQueue;

  @Value("${mq.config.fanout.queue.sms}")
  private String smsQueue;

  @Value("${mq.config.fanout.queue.push}")
  private String pushQueue;

  /**
   * 创建扇出交换机
   */
  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange(fanoutExchange, true, false);
  }

  /**
   * 创建邮件队列
   */
  @Bean
  public Queue emailQueue() {
    return QueueBuilder.durable(emailQueue).build();
  }

  /**
   * 创建短信队列
   */
  @Bean
  public Queue smsQueue() {
    return QueueBuilder.durable(smsQueue).build();
  }

  /**
   * 创建推送队列
   */
  @Bean
  public Queue pushQueue() {
    return QueueBuilder.durable(pushQueue).build();
  }

  /**
   * 将邮件队列绑定到扇出交换机
   * 扇出交换机不需要路由键，会将消息发送到所有绑定的队列
   */
  @Bean
  public Binding emailBinding() {
    return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
  }

  /**
   * 将短信队列绑定到扇出交换机
   */
  @Bean
  public Binding smsBinding() {
    return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
  }

  /**
   * 将推送队列绑定到扇出交换机
   */
  @Bean
  public Binding pushBinding() {
    return BindingBuilder.bind(pushQueue()).to(fanoutExchange());
  }
}
