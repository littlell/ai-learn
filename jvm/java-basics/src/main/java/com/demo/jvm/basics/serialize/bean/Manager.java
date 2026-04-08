package com.demo.jvm.basics.serialize.bean;

import java.io.Serializable;

public class Manager extends Member implements Serializable {

  private int level;

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  @Override
  public String toString() {
    return "Manager{" +
        "level=" + level +
        ", name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
