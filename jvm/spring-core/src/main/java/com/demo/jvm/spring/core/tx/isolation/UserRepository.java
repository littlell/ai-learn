package com.demo.jvm.spring.core.tx.isolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

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

  public Integer selectAgeByName(String name) {
    Map resp;
    try {
      resp = jdbcTemplate.queryForMap("select age from user where name=? limit 1", name);
    } catch (Exception e) {
      return null;
    }
    return (Integer) resp.get("age");
  }
}
