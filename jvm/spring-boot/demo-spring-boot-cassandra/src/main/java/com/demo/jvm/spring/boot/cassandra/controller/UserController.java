package com.demo.jvm.spring.boot.cassandra.controller;

import com.demo.jvm.spring.boot.cassandra.entity.User;
import com.demo.jvm.spring.boot.cassandra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 用户控制器 - 简单示例
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * 创建用户
   */
  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.saveUser(user);
  }

  /**
   * 获取所有用户
   */
  @GetMapping
  public List<User> getAllUsers() {
    return userService.findAll();
  }

  /**
   * 根据ID获取用户
   */
  @GetMapping("/{id}")
  public User getUserById(@PathVariable UUID id) {
    return userService.findById(id).orElse(null);
  }

  /**
   * 根据用户名获取用户
   */
  @GetMapping("/username/{username}")
  public User getUserByUsername(@PathVariable String username) {
    return userService.findByUsername(username).orElse(null);
  }

  /**
   * 根据年龄查找用户
   */
  @GetMapping("/age/{age}")
  public List<User> getUsersByAge(@PathVariable Integer age) {
    return userService.findByAge(age);
  }

  /**
   * 删除用户
   */
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable UUID id) {
    userService.deleteById(id);
  }
}
