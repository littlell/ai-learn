package com.demo.jvm.spring.core.tx.isolation;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {
  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {
    User user = new User();
    user.setName(resultSet.getString("name"));
    user.setAge(resultSet.getInt("age"));
    return user;
  }
}
