package com.demo.jvm.spring.boot.cassandra.config;

import com.demo.jvm.spring.boot.cassandra.entity.User;
import com.demo.jvm.spring.boot.cassandra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 数据初始化器 - 简单示例
 */
@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  private UserService userService;

  @Override
  public void run(String... args) throws Exception {
    try {
      System.out.println("开始初始化测试数据...");
      
      // 创建几个测试用户
      User user1 = new User("张三", "zhangsan@example.com", 25);
      User user2 = new User("李四", "lisi@example.com", 30);
      User user3 = new User("王五", "wangwu@example.com", 28);
      
      userService.saveUser(user1);
      userService.saveUser(user2);
      userService.saveUser(user3);
      
      System.out.println("测试数据初始化完成！创建了3个用户。");
      
    } catch (Exception e) {
      System.err.println("数据初始化失败: " + e.getMessage());
      // 不要抛出异常，让应用继续启动
    }
  }
}
