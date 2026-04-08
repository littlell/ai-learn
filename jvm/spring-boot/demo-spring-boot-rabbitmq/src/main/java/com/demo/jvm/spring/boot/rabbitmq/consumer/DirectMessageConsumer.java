package com.demo.jvm.spring.boot.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 直连交换机消息消费者
 */
@Component
public class DirectMessageConsumer {

  /**
   * 监听info队列消息
   */
  @RabbitListener(queues = "${mq.config.direct.queue.info}")
  public void receiveInfoMessage(String message) {
    System.out.println("===== INFO队列收到消息 =====");
    System.out.println("消息内容: " + message);
    System.out.println("处理INFO级别的业务逻辑...");
    System.out.println("===========================\n");
  }

  /**
   * 监听warning队列消息
   */
  @RabbitListener(queues = "${mq.config.direct.queue.warning}")
  public void receiveWarningMessage(String message) {
    System.out.println("===== WARNING队列收到消息 =====");
    System.out.println("消息内容: " + message);
    System.out.println("处理WARNING级别的业务逻辑...");
    System.out.println("可能需要发送告警通知!");
    System.out.println("===============================\n");
  }

  /**
   * 监听error队列消息
   */
  @RabbitListener(queues = "${mq.config.direct.queue.error}")
  public void receiveErrorMessage(String message) {
    System.out.println("===== ERROR队列收到消息 =====");
    System.out.println("消息内容: " + message);
    System.out.println("处理ERROR级别的业务逻辑...");
    System.out.println("需要立即处理错误并发送紧急通知!");
    System.out.println("=============================\n");
  }
}
