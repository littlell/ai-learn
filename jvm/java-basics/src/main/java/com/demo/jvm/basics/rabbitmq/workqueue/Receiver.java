package com.demo.jvm.basics.rabbitmq.workqueue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {
  private static final String QUEUE_NAME = "task_queue";
  private static final String HOST = "192.168.64.60";

  /** Created by xialei on 29/09/2016. */
  public static void main(String[] args) throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(HOST);
    Connection connection = factory.newConnection();
    final Channel channel = connection.createChannel();

    // Message durability
    boolean durable = true;
    channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

    // Fair dispatch
    int prefetchCount = 1;
    channel.basicQos(prefetchCount);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer =
        new DefaultConsumer(channel) {
          @Override
          // messages persistent
          public void handleDelivery(
              String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
            String message = new String(body, "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            try {
              Thread.sleep(10000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            // 手动回复, 取消自动回复时， 漏掉会有问题， 占用内存
            channel.basicAck(envelope.getDeliveryTag(), false);
          }
        };
    // 设置autoAck=false
    channel.basicConsume(QUEUE_NAME, false, consumer);
  }
}
