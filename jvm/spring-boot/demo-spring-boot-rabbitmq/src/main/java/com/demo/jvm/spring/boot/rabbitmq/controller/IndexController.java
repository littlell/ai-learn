package com.demo.jvm.spring.boot.rabbitmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主页控制器
 */
@RestController
public class IndexController {

  /**
   * 主页 - 显示所有交换机类型的测试链接
   */
  @GetMapping("/")
  public String index() {
    return "<h1>RabbitMQ交换机测试中心</h1>" +
           "<p>选择要测试的交换机类型:</p>" +
           
           "<h3>直连交换机 (Direct Exchange)</h3>" +
           "<p>根据路由键精确匹配，适用于日志分级处理</p>" +
           "<ul>" +
           "<li><a href='/direct/'>进入直连交换机测试页面</a></li>" +
           "<li><a href='/direct/info?message=用户登录成功'>快速测试 - 发送INFO消息</a></li>" +
           "<li><a href='/direct/warning?message=系统负载较高'>快速测试 - 发送WARNING消息</a></li>" +
           "<li><a href='/direct/error?message=数据库连接失败'>快速测试 - 发送ERROR消息</a></li>" +
           "<li><a href='/direct/test'>快速测试 - 批量发送测试消息</a></li>" +
           "</ul>" +
           
           "<h3>扇出交换机 (Fanout Exchange)</h3>" +
           "<p>广播到所有绑定队列，适用于多渠道通知</p>" +
           "<ul>" +
           "<li><a href='/fanout/'>进入扇出交换机测试页面</a></li>" +
           "<li><a href='/fanout/broadcast?message=系统维护通知'>快速测试 - 发送广播消息</a></li>" +
           "<li><a href='/fanout/test'>快速测试 - 发送测试消息</a></li>" +
           "</ul>" +
           
           "<h3>主题交换机 (Topic Exchange)</h3>" +
           "<p>支持通配符路由，灵活匹配消息类型</p>" +
           "<ul>" +
           "<li><a href='/topic/'>进入主题交换机测试页面</a></li>" +
           "<li><a href='/topic/order/create?orderId=ORDER123'>快速测试 - 发送订单创建消息</a></li>" +
           "<li><a href='/topic/user/login?username=张三'>快速测试 - 发送用户登录消息</a></li>" +
           "<li><a href='/topic/test'>快速测试 - 批量发送测试消息</a></li>" +
           "</ul>" +
           
           "<h3>帮助信息</h3>" +
           "<ul>" +
           "<li><a href='/direct/help'>直连交换机API说明</a></li>" +
           "<li><a href='/fanout/help'>扇出交换机API说明</a></li>" +
           "<li><a href='/topic/help'>主题交换机API说明</a></li>" +
           "<li><a href='/help'>完整API文档</a></li>" +
           "</ul>";
  }

  /**
   * 完整API文档
   */
  @GetMapping("/help")
  public String getHelp() {
    return "RabbitMQ交换机测试API完整文档:\n\n" +
           "=== 直连交换机 (Direct Exchange) ===\n" +
           "特点: 根据路由键精确匹配\n" +
           "1. GET /direct/info?message=消息内容 - 发送INFO消息\n" +
           "2. GET /direct/warning?message=消息内容 - 发送WARNING消息\n" +
           "3. GET /direct/error?message=消息内容 - 发送ERROR消息\n" +
           "4. GET /direct/test - 发送直连测试消息\n" +
           "5. GET /direct/ - 直连交换机测试页面\n" +
           "6. GET /direct/help - 直连交换机帮助\n\n" +
           
           "=== 扇出交换机 (Fanout Exchange) ===\n" +
           "特点: 广播到所有绑定队列，忽略路由键\n" +
           "1. GET /fanout/broadcast?message=消息内容 - 发送广播消息\n" +
           "2. GET /fanout/test - 发送扇出测试消息\n" +
           "3. GET /fanout/ - 扇出交换机测试页面\n" +
           "4. GET /fanout/help - 扇出交换机帮助\n\n" +
           
           "=== 主题交换机 (Topic Exchange) ===\n" +
           "特点: 支持通配符路由，* 匹配一个单词，# 匹配零个或多个单词\n" +
           "1. GET /topic/order/create?orderId=订单ID - 发送订单创建消息\n" +
           "2. GET /topic/order/cancel?orderId=订单ID - 发送订单取消消息\n" +
           "3. GET /topic/user/login?username=用户名 - 发送用户登录消息\n" +
           "4. GET /topic/user/logout?username=用户名 - 发送用户登出消息\n" +
           "5. GET /topic/test - 发送主题测试消息\n" +
           "6. GET /topic/ - 主题交换机测试页面\n" +
           "7. GET /topic/help - 主题交换机帮助\n\n" +
           
           "=== 其他 ===\n" +
           "1. GET / - 主页\n" +
           "2. GET /help - 完整API文档\n\n" +
           
           "浏览器测试示例:\n" +
           "http://localhost:9000/direct/info?message=用户登录成功\n" +
           "http://localhost:9000/fanout/broadcast?message=系统维护通知\n" +
           "http://localhost:9000/topic/order/create?orderId=ORDER123\n\n" +
           
           "应用场景:\n" +
           "- 直连交换机: 日志分级、任务分类、条件路由\n" +
           "- 扇出交换机: 多渠道通知、数据同步、广播消息\n" +
           "- 主题交换机: 复杂路由、分类订阅、灵活匹配";
  }
}
