package com.demo.jvm.spring.boot.rabbitmq.controller;

import com.demo.jvm.spring.boot.rabbitmq.producer.DirectMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 直连交换机测试控制器
 */
@RestController
@RequestMapping("/direct")
public class DirectMessageController {

  @Autowired
  private DirectMessageProducer directMessageProducer;

  /**
   * 发送info级别消息
   */
  @GetMapping("/info")
  public String sendInfoMessage(@RequestParam(defaultValue = "默认INFO消息") String message) {
    directMessageProducer.sendInfoMessage(message);
    return "INFO消息已发送: " + message;
  }

  /**
   * 发送warning级别消息
   */
  @GetMapping("/warning")
  public String sendWarningMessage(@RequestParam(defaultValue = "默认WARNING消息") String message) {
    directMessageProducer.sendWarningMessage(message);
    return "WARNING消息已发送: " + message;
  }

  /**
   * 发送error级别消息
   */
  @GetMapping("/error")
  public String sendErrorMessage(@RequestParam(defaultValue = "默认ERROR消息") String message) {
    directMessageProducer.sendErrorMessage(message);
    return "ERROR消息已发送: " + message;
  }

  /**
   * 批量发送测试消息
   */
  @GetMapping("/test")
  public String sendDirectTestMessages() {
    directMessageProducer.sendInfoMessage("这是一条测试信息消息");
    directMessageProducer.sendWarningMessage("这是一条测试警告消息");
    directMessageProducer.sendErrorMessage("这是一条测试错误消息");
    
    return "直连交换机测试消息已全部发送完成!";
  }

  /**
   * 直连交换机首页
   */
  @GetMapping("/")
  public String index() {
    return "<h2>直连交换机测试页面</h2>" +
           "<p>点击以下链接进行测试:</p>" +
           "<ul>" +
           "<li><a href='/direct/info'>发送INFO消息 (默认消息)</a></li>" +
           "<li><a href='/direct/info?message=用户登录成功'>发送INFO消息 (自定义消息)</a></li>" +
           "<li><a href='/direct/warning'>发送WARNING消息 (默认消息)</a></li>" +
           "<li><a href='/direct/warning?message=系统负载较高'>发送WARNING消息 (自定义消息)</a></li>" +
           "<li><a href='/direct/error'>发送ERROR消息 (默认消息)</a></li>" +
           "<li><a href='/direct/error?message=数据库连接失败'>发送ERROR消息 (自定义消息)</a></li>" +
           "<li><a href='/direct/test'>批量发送测试消息</a></li>" +
           "<li><a href='/direct/help'>查看API说明</a></li>" +
           "</ul>" +
           "<p><a href='/'>返回主页</a></p>";
  }

  /**
   * 获取使用说明
   */
  @GetMapping("/help")
  public String getHelp() {
    return "直连交换机测试API使用说明:\n" +
           "1. GET /direct/info?message=消息内容 - 发送INFO消息\n" +
           "2. GET /direct/warning?message=消息内容 - 发送WARNING消息\n" +
           "3. GET /direct/error?message=消息内容 - 发送ERROR消息\n" +
           "4. GET /direct/test - 发送测试消息\n" +
           "5. GET /direct/ - 显示测试页面\n" +
           "6. GET /direct/help - 查看帮助信息\n\n" +
           "浏览器测试示例:\n" +
           "http://localhost:9000/direct/info?message=用户登录成功\n" +
           "http://localhost:9000/direct/warning?message=系统负载较高\n" +
           "http://localhost:9000/direct/error?message=数据库连接失败";
  }
}
