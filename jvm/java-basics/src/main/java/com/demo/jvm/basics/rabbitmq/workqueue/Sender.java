package com.demo.jvm.basics.rabbitmq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class Sender {
  private static final String QUEUE_NAME = "task_queue";
  private static final String HOST = "192.168.64.60";

  /** Created by xialei on 29/09/2016. */
  public static void main(String[] args) throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(HOST);
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    boolean durable = true;
    channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
    String message = "Hello World!" + new Date();
    channel.basicPublish(
        "", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
}
