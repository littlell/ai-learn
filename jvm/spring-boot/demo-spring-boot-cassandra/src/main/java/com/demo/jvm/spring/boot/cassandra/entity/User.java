package com.demo.jvm.spring.boot.cassandra.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

/**
 * 用户实体类 - 简单示例
 */
@Table("users")
public class User {

  @PrimaryKey
  private UUID id;

  private String username;
  private String email;
  private Integer age;

  // 默认构造函数
  public User() {
    this.id = UUID.randomUUID();
  }

  // 带参数构造函数
  public User(String username, String email, Integer age) {
    this();
    this.username = username;
    this.email = email;
    this.age = age;
  }

  // Getter 和 Setter 方法
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", age=" + age +
        '}';
  }
}
