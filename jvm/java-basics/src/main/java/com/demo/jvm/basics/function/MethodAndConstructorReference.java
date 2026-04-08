package com.demo.jvm.basics.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodAndConstructorReference {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("a");
    list.add("b");

    // method reference
    Consumer method = System.out::println;
    list.stream().forEach(e -> method.accept(e));

    // constructor reference
    Function<String, Person> creator = Person::new;
    list.stream().map(creator).forEach(e -> method.accept(e));
  }

  public static class Person {
    private String name;

    @Override
    public String toString() {
      return "Person{" +
          "name='" + name + '\'' +
          '}';
    }

    public Person(String name) {
      this.name = name;
    }
  }
}
