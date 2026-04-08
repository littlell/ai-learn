package com.demo.jvm.spring.boot.cassandra.service;

import com.demo.jvm.spring.boot.cassandra.entity.User;
import com.demo.jvm.spring.boot.cassandra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 用户服务类 - 简单示例
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * 创建用户
   */
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  /**
   * 根据ID查找用户
   */
  public Optional<User> findById(UUID id) {
    return userRepository.findById(id);
  }

  /**
   * 根据用户名查找用户
   */
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  /**
   * 获取所有用户
   */
  public List<User> findAll() {
    return userRepository.findAll();
  }

  /**
   * 根据年龄查找用户
   */
  public List<User> findByAge(Integer age) {
    return userRepository.findByAge(age);
  }

  /**
   * 删除用户
   */
  public void deleteById(UUID id) {
    userRepository.deleteById(id);
  }
}
