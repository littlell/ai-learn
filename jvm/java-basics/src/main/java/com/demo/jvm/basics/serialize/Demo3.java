package com.demo.jvm.basics.serialize;

import com.demo.jvm.basics.serialize.bean.Company;
import com.demo.jvm.basics.serialize.bean.Department;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Bean property should implement Serializable.
 */
public class Demo3 {

  public static void main(String[] args) throws IOException, ClassNotFoundException {

    Company company = new Company();
    company.setName("IBM");
    company.setAddress("USA");
    company.setDepartmentList(new ArrayList<>() {{
      Department department = new Department();
      department.setName("biz");
      department.setMemberCount(100);
      add(department);
    }});

    File file = File.createTempFile("out", "tmp");

    FileOutputStream fileOutputStream = new FileOutputStream(file);

    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

    objectOutputStream.writeObject(company);

    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
    Company newCompany = (Company) objectInputStream.readObject();

    System.out.println(newCompany);
  }

}
