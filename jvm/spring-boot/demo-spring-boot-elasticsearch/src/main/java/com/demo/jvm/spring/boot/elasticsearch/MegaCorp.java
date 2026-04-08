package com.demo.jvm.spring.boot.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
import java.util.Objects;

/**
 * MegaCorp 实体类 - 使用现代 Java 语法优化
 * 使用了标准的 getter/setter，并添加了 equals、hashCode 和 toString 方法
 */
@Document(indexName = "megacorp")
public class MegaCorp {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private Integer age;
  private String about;
  private List<String> interests;

  public MegaCorp() {
  }

  public MegaCorp(String id, String firstName, String lastName, Integer age, String about, List<String> interests) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.about = about;
    this.interests = interests;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public List<String> getInterests() {
    return interests;
  }

  public void setInterests(List<String> interests) {
    this.interests = interests;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MegaCorp megaCorp = (MegaCorp) o;
    return Objects.equals(id, megaCorp.id) &&
        Objects.equals(firstName, megaCorp.firstName) &&
        Objects.equals(lastName, megaCorp.lastName) &&
        Objects.equals(age, megaCorp.age) &&
        Objects.equals(about, megaCorp.about) &&
        Objects.equals(interests, megaCorp.interests);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, age, about, interests);
  }

  @Override
  public String toString() {
    return "MegaCorp{" +
        "id='" + id + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", age=" + age +
        ", about='" + about + '\'' +
        ", interests=" + interests +
        '}';
  }
}
