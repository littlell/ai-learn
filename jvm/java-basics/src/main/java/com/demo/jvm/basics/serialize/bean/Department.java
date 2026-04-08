package com.demo.jvm.basics.serialize.bean;

import java.io.Serializable;

public class Department implements Serializable {

  private String name;

  private int memberCount;

  public int getMemberCount() {
    return memberCount;
  }

  public void setMemberCount(int memberCount) {
    this.memberCount = memberCount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Department{" +
        "name='" + name + '\'' +
        ", memberCount=" + memberCount +
        '}';
  }
}
