package com.demo.jvm.spring.boot.rabbitmq.controller;

import com.demo.jvm.spring.boot.rabbitmq.producer.FanoutMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 扇出交换机测试控制器
 */
@RestController
@RequestMapping("/fanout")
public class FanoutMessageController {

  @Autowired
  private FanoutMessageProducer fanoutMessageProducer;

  /**
   * 发送广播消息
   */
  @GetMapping("/broadcast")
  public String sendBroadcastMessage(@RequestParam(defaultValue = "默认广播消息") String message) {
    fanoutMessageProducer.sendBroadcastMessage(message);
    return "广播消息已发送: " + message + " (将发送到所有队列: 邮件、短信、推送)";
  }

  /**
   * 扇出交换机测试
   */
  @GetMapping("/test")
  public String sendFanoutTestMessages() {
    fanoutMessageProducer.sendBroadcastMessage("这是一条测试广播消息");
    return "扇出交换机测试消息已发送完成!";
  }

  /**
   * 扇出交换机首页
   */
  @GetMapping("/")
  public String index() {
    return "<h2>扇出交换机测试页面</h2>" +
           "<p>点击以下链接进行测试:</p>" +
           "<ul>" +
           "<li><a href='/fanout/broadcast'>发送广播消息 (默认消息)</a></li>" +
           "<li><a href='/fanout/broadcast?message=系统维护通知'>发送广播消息 (自定义消息)</a></li>" +
           "<li><a href='/fanout/test'>发送测试消息</a></li>" +
           "<li><a href='/fanout/help'>查看API说明</a></li>" +
           "</ul>" +
           "<p><a href='/'>返回主页</a></p>";
  }

  /**
   * 获取使用说明
   */
  @GetMapping("/help")
  public String getHelp() {
    return "扇出交换机测试API使用说明:\n" +
           "1. GET /fanout/broadcast?message=消息内容 - 发送广播消息\n" +
           "2. GET /fanout/test - 发送测试消息\n" +
           "3. GET /fanout/ - 显示测试页面\n" +
           "4. GET /fanout/help - 查看帮助信息\n\n" +
           "浏览器测试示例:\n" +
           "http://localhost:9000/fanout/broadcast?message=系统维护通知\n" +
           "http://localhost:9000/fanout/test\n\n" +
           "特点: 扇出交换机将消息广播到所有绑定的队列(邮件、短信、推送)，忽略路由键";
  }
}
