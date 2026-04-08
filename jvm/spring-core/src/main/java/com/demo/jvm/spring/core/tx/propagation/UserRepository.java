package com.demo.jvm.spring.core.tx.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void insert(String name, int age) {
    jdbcTemplate.update("insert into user (name, age) values (?, ?)", name, age);
  }

  public void update(int id, String name, int age) {
    jdbcTemplate.update("update user set name = ? and age = ? where id = ?", name, age, id);
  }
}
