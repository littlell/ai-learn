package com.demo.jvm.basics.serialize;

import com.demo.jvm.basics.serialize.bean.Dog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * If parent class implement Serializable , all child classes can be serialized.
 */
public class Demo1 {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Dog dog = new Dog();

    dog.setType("home");
    dog.setAge(1);
    dog.setGender("mail");

    File file = File.createTempFile("out", "tmp");

    FileOutputStream fileOutputStream = new FileOutputStream(file);

    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

    objectOutputStream.writeObject(dog);

    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
    Dog newDog = (Dog) objectInputStream.readObject();

    System.out.println(newDog);
  }
}
