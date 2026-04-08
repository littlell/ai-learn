package com.demo.jvm.basics.serialize.bean;

import java.io.Serializable;
import java.util.List;

public class Company implements Serializable {

  private static final long serialVersionUID = -5175091053719822983L;
  private String name;

  private String address;

  private List<Department> departmentList;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Department> getDepartmentList() {
    return departmentList;
  }

  public void setDepartmentList(List<Department> departmentList) {
    this.departmentList = departmentList;
  }

  @Override
  public String toString() {
    return "Company{" +
        "name='" + name + '\'' +
        ", address='" + address + '\'' +
        ", departmentList=" + departmentList +
        '}';
  }
}
