package com.demo.jvm.spring.boot.mongodb;

import com.demo.jvm.spring.boot.mongodb.entity.Customer;
import com.demo.jvm.spring.boot.mongodb.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Demo Spring Boot 05 - MongoDB 数据库操作示例
 */
@SpringBootApplication
public class App implements CommandLineRunner {

  @Autowired
  private CustomerService customerService;

  private final static Logger log = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("开始演示 MongoDB 客户管理系统...");

    // 清空所有数据
    customerService.deleteAllCustomers();
    log.info("已清空所有客户数据");

    // 保存几个客户
    Customer customer1 = new Customer("张三", "王", "zhangsan@example.com", 25);
    Customer customer2 = new Customer("李四", "李", "lisi@example.com", 30);
    Customer customer3 = new Customer("王五", "王", "wangwu@example.com", 28);
    Customer customer4 = new Customer("赵六", "赵", "zhaoliu@example.com", 35);

    customerService.saveCustomer(customer1);
    customerService.saveCustomer(customer2);
    customerService.saveCustomer(customer3);
    customerService.saveCustomer(customer4);

    log.info("已保存 {} 个客户", customerService.getCustomerCount());

    // 查询所有客户
    log.info("=== 所有客户 ===");
    customerService.getAllCustomers().forEach(customer -> log.info(customer.toString()));

    // 根据名字查询
    log.info("=== 根据名字查询（张三）===");
    Customer foundCustomer = customerService.getCustomerByFirstName("张三");
    if (foundCustomer != null) {
      log.info(foundCustomer.toString());
    }

    // 根据姓氏查询
    log.info("=== 根据姓氏查询（王）===");
    customerService.getCustomersByLastName("王").forEach(customer -> log.info(customer.toString()));

    // 根据年龄查询
    log.info("=== 根据年龄查询（30岁）===");
    customerService.getCustomersByAge(30).forEach(customer -> log.info(customer.toString()));

    // 根据年龄范围查询
    log.info("=== 根据年龄范围查询（25-32岁）===");
    customerService.getCustomersByAgeBetween(25, 32).forEach(customer -> log.info(customer.toString()));

    log.info("MongoDB 客户管理系统演示完成！");
    log.info("现在可以通过 REST API 访问系统：http://localhost:8080/api/customers");
  }
}
