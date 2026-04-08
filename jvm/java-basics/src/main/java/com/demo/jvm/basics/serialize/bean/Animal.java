package com.demo.jvm.basics.serialize.bean;

import java.io.Serializable;

public class Animal implements Serializable {

  int age;

  String gender;

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
