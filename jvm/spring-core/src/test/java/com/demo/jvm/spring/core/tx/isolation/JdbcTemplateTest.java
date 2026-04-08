package com.demo.jvm.spring.core.tx.isolation;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/tx/isolation/applicationContext.xml")
public class JdbcTemplateTest {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void index() {
    System.out.println(jdbcTemplate);
  }

  @Test
  public void create() {
    String name = "foo";
    int age = 20;
    String SQL = "insert into user (name, age) values (?, ?)";
    jdbcTemplate.update(SQL, name, age);
    System.out.println("Created Record Name = " + name + " Age = " + age);
  }

  @Test
  public void getUser() {
    int id = 1;
    String SQL = "select * from user where id = ?";
    User user = jdbcTemplate.queryForObject(SQL,
        new Object[]{id}, new UserMapper());
    System.out.println(user);
  }

  @Test
  public void listStudents() {
    String SQL = "select * from user";
    List<User> students = jdbcTemplate.query(SQL, new UserMapper());
    System.out.println(students);
  }

  @Test
  public void delete() {
    int id = 1;
    String SQL = "delete from user where id = ?";
    jdbcTemplate.update(SQL, id);
    System.out.println("Deleted Record with ID = " + id);
  }

  @Test
  public void update() {
    int id = 1;
    int age = 50;
    String SQL = "update user set age = ? where id = ?";
    jdbcTemplate.update(SQL, age, id);
    System.out.println("Updated Record with ID = " + id);
  }

}
