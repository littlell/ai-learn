package com.demo.jvm.spring.boot.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/db")
public class DbController {
  private final JdbcTemplate jdbcTemplate;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public DbController(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @GetMapping("/count")
  public Integer getUserCount() {
    return jdbcTemplate.queryForObject("select count(*) from user;", Integer.class);
  }

  @GetMapping("/insert")
  public int insertUser() {
    Map<String, Object> paramMap = Map.of(
        "name", "foo",
        "age", 12
    );
    return namedParameterJdbcTemplate.update("insert into user (name, age) values(:name, :age)", paramMap);
  }
}
