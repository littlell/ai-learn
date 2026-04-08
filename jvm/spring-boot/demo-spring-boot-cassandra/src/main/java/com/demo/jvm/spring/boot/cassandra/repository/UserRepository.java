package com.demo.jvm.spring.boot.cassandra.repository;

import com.demo.jvm.spring.boot.cassandra.entity.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 用户数据访问接口 - 简单示例
 */
@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {

  /**
   * 根据用户名查找用户
   */
  @Query("SELECT * FROM users WHERE username = ?0 ALLOW FILTERING")
  Optional<User> findByUsername(String username);

  /**
   * 查找指定年龄的用户
   */
  @Query("SELECT * FROM users WHERE age = ?0 ALLOW FILTERING")
  List<User> findByAge(Integer age);
}
