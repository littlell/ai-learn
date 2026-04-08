package com.demo.jvm.basics.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Sender {
  private static final String HOST = "192.168.64.60";
  private static final String EXCHANGE_NAME = "logs";

  /** Created by xialei on 29/09/2016. */
  public static void main(String[] args) throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(HOST);
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, "direct");

    String[] routingKeys = new String[] {"info", "error", "warning"};
    // 使用 SecureRandom，避免弱随机数（即使当前仅用于路由键选择）
    int index = new SecureRandom().nextInt(routingKeys.length);

    String severity = routingKeys[index];
    String message = "message";
    //
    channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
    //        System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
}
