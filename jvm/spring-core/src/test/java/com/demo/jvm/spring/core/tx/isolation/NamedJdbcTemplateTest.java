package com.demo.jvm.spring.core.tx.isolation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/tx/isolation/applicationContext.xml")
public class NamedJdbcTemplateTest {
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Test
  public void index() {
    System.out.println(namedParameterJdbcTemplate);
  }

  @Test
  public void create() {
    String name = "foo";
    int age = 20;
    String SQL = "insert into user (name, age) values (:name, :age)";
    Map<String, Object> paramsMap = new HashMap<>();
    paramsMap.put("name", name);
    paramsMap.put("age", age);
    namedParameterJdbcTemplate.update(SQL, paramsMap);
    System.out.println("Created Record Name = " + name + " Age = " + age);
  }
//
//  @Test
//  public void getUser() {
//    int id = 1;
//    String SQL = "select * from user where id = ?";
//    User user = namedParameterJdbcTemplate.queryForObject(SQL,
//        new Object[]{id}, new UserMapper());
//    System.out.println(user);
//  }
//
//  @Test
//  public void listStudents() {
//    String SQL = "select * from user";
//    List<User> students = namedParameterJdbcTemplate.query(SQL, new UserMapper());
//    System.out.println(students);
//  }
//
//  @Test
//  public void delete() {
//    int id = 1;
//    String SQL = "delete from user where id = ?";
//    namedParameterJdbcTemplate.update(SQL, id);
//    System.out.println("Deleted Record with ID = " + id);
//  }
//
//  @Test
//  public void update() {
//    int id = 2;
//    int age = 50;
//    String SQL = "update user set age = ? where id = ?";
//    namedParameterJdbcTemplate.update(SQL, age, id);
//    System.out.println("Updated Record with ID = " + id);
//  }

}
