package com.demo.jvm.basics.serialize;

import com.demo.jvm.basics.serialize.bean.Manager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * If only child class implement Serializable, child class can be serialized, but properties of
 * parent class will loss.
 */
public class Demo2 {

  public static void main(String[] args) throws IOException, ClassNotFoundException {

    Manager manager = new Manager();

    manager.setName("John");
    manager.setAge(1);
    manager.setLevel(3);

    File file = File.createTempFile("out", "tmp");

    FileOutputStream fileOutputStream = new FileOutputStream(file);

    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

    objectOutputStream.writeObject(manager);

    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
    Manager newManager = (Manager) objectInputStream.readObject();

    System.out.println(newManager);
  }

}
