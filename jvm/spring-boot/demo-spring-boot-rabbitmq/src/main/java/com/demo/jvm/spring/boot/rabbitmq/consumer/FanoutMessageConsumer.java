package com.demo.jvm.spring.boot.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 扇出交换机消息消费者
 */
@Component
public class FanoutMessageConsumer {

  /**
   * 监听邮件队列消息
   */
  @RabbitListener(queues = "${mq.config.fanout.queue.email}")
  public void receiveEmailMessage(String message) {
    System.out.println("===== 邮件服务收到消息 =====");
    System.out.println("消息内容: " + message);
    System.out.println("正在发送邮件通知...");
    System.out.println("邮件发送完成！");
    System.out.println("===========================\n");
  }

  /**
   * 监听短信队列消息
   */
  @RabbitListener(queues = "${mq.config.fanout.queue.sms}")
  public void receiveSmsMessage(String message) {
    System.out.println("===== 短信服务收到消息 =====");
    System.out.println("消息内容: " + message);
    System.out.println("正在发送短信通知...");
    System.out.println("短信发送完成！");
    System.out.println("===========================\n");
  }

  /**
   * 监听推送队列消息
   */
  @RabbitListener(queues = "${mq.config.fanout.queue.push}")
  public void receivePushMessage(String message) {
    System.out.println("===== 推送服务收到消息 =====");
    System.out.println("消息内容: " + message);
    System.out.println("正在发送推送通知...");
    System.out.println("推送发送完成！");
    System.out.println("===========================\n");
  }
}
