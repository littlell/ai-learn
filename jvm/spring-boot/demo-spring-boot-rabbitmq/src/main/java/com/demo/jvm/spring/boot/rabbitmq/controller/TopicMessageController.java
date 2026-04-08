package com.demo.jvm.spring.boot.rabbitmq.controller;

import com.demo.jvm.spring.boot.rabbitmq.producer.TopicMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 主题交换机测试控制器
 */
@RestController
@RequestMapping("/topic")
public class TopicMessageController {

  @Autowired
  private TopicMessageProducer topicMessageProducer;

  /**
   * 发送订单创建消息
   */
  @GetMapping("/order/create")
  public String sendOrderCreateMessage(@RequestParam(defaultValue = "ORDER001") String orderId) {
    topicMessageProducer.sendOrderCreateMessage(orderId);
    return "订单创建消息已发送: " + orderId + " (路由键: order.create，匹配 order.* 和 # 模式)";
  }

  /**
   * 发送订单取消消息
   */
  @GetMapping("/order/cancel")
  public String sendOrderCancelMessage(@RequestParam(defaultValue = "ORDER002") String orderId) {
    topicMessageProducer.sendOrderCancelMessage(orderId);
    return "订单取消消息已发送: " + orderId + " (路由键: order.cancel，匹配 order.* 和 # 模式)";
  }

  /**
   * 发送用户登录消息
   */
  @GetMapping("/user/login")
  public String sendUserLoginMessage(@RequestParam(defaultValue = "testuser") String username) {
    topicMessageProducer.sendUserLoginMessage(username);
    return "用户登录消息已发送: " + username + " (路由键: user.login，匹配 user.* 和 # 模式)";
  }

  /**
   * 发送用户登出消息
   */
  @GetMapping("/user/logout")
  public String sendUserLogoutMessage(@RequestParam(defaultValue = "testuser") String username) {
    topicMessageProducer.sendUserLogoutMessage(username);
    return "用户登出消息已发送: " + username + " (路由键: user.logout，匹配 user.* 和 # 模式)";
  }

  /**
   * 主题交换机批量测试
   */
  @GetMapping("/test")
  public String sendTopicTestMessages() {
    topicMessageProducer.sendOrderCreateMessage("TEST_ORDER_001");
    topicMessageProducer.sendOrderCancelMessage("TEST_ORDER_002");
    topicMessageProducer.sendUserLoginMessage("test_user");
    topicMessageProducer.sendUserLogoutMessage("test_user");
    
    return "主题交换机测试消息已全部发送完成!";
  }

  /**
   * 主题交换机首页
   */
  @GetMapping("/")
  public String index() {
    return "<h2>主题交换机测试页面</h2>" +
           "<p>主题交换机支持通配符路由，更加灵活：</p>" +
           "<ul>" +
           "<li><strong>*</strong> (星号) - 匹配一个单词</li>" +
           "<li><strong>#</strong> (井号) - 匹配零个或多个单词</li>" +
           "</ul>" +
           "<h3>测试链接:</h3>" +
           "<ul>" +
           "<li><a href='/topic/order/create'>发送订单创建消息 (默认订单)</a></li>" +
           "<li><a href='/topic/order/create?orderId=ORDER123'>发送订单创建消息 (自定义订单)</a></li>" +
           "<li><a href='/topic/order/cancel'>发送订单取消消息 (默认订单)</a></li>" +
           "<li><a href='/topic/order/cancel?orderId=ORDER456'>发送订单取消消息 (自定义订单)</a></li>" +
           "<li><a href='/topic/user/login'>发送用户登录消息 (默认用户)</a></li>" +
           "<li><a href='/topic/user/login?username=张三'>发送用户登录消息 (自定义用户)</a></li>" +
           "<li><a href='/topic/user/logout'>发送用户登出消息 (默认用户)</a></li>" +
           "<li><a href='/topic/user/logout?username=张三'>发送用户登出消息 (自定义用户)</a></li>" +
           "<li><a href='/topic/test'>批量发送测试消息</a></li>" +
           "<li><a href='/topic/help'>查看API说明</a></li>" +
           "</ul>" +
           "<h3>路由模式说明:</h3>" +
           "<ul>" +
           "<li><strong>订单队列</strong>: 绑定 order.* (接收 order.create, order.cancel 等)</li>" +
           "<li><strong>用户队列</strong>: 绑定 user.* (接收 user.login, user.logout 等)</li>" +
           "<li><strong>全部队列</strong>: 绑定 # (接收所有消息，用于日志记录)</li>" +
           "</ul>" +
           "<p><a href='/'>返回主页</a></p>";
  }

  /**
   * 获取使用说明
   */
  @GetMapping("/help")
  public String getHelp() {
    return "主题交换机测试API使用说明:\n" +
           "1. GET /topic/order/create?orderId=订单ID - 发送订单创建消息\n" +
           "2. GET /topic/order/cancel?orderId=订单ID - 发送订单取消消息\n" +
           "3. GET /topic/user/login?username=用户名 - 发送用户登录消息\n" +
           "4. GET /topic/user/logout?username=用户名 - 发送用户登出消息\n" +
           "5. GET /topic/test - 发送测试消息\n" +
           "6. GET /topic/ - 显示测试页面\n" +
           "7. GET /topic/help - 查看帮助信息\n\n" +
           "路由键模式:\n" +
           "- order.* : 匹配所有订单相关消息\n" +
           "- user.* : 匹配所有用户相关消息\n" +
           "- # : 匹配所有消息\n\n" +
           "浏览器测试示例:\n" +
           "http://localhost:9000/topic/order/create?orderId=ORDER123\n" +
           "http://localhost:9000/topic/user/login?username=张三\n\n" +
           "特点: 主题交换机支持通配符匹配，* 匹配一个单词，# 匹配零个或多个单词";
  }
}
