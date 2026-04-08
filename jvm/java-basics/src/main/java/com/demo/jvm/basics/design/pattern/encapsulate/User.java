package com.demo.jvm.basics.design.pattern.encapsulate;

/** Created by littlell on 2017/3/12. */
public class User implements Comparable {
  private String name;
  private int age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
  }

  @Override
  public int compareTo(Object o) {
    User user = (User) o;
    if (this.age < user.getAge()) {
      return -1;
    } else if (this.age == user.getAge()) {
      return 0;
    } else {
      return 1;
    }
  }
}
