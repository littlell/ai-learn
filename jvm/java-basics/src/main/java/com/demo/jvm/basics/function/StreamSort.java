package com.demo.jvm.basics.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSort {
  public static void main(String[] args) {
    int[] numbers = {3, 6, 8, 8, 4, 6, 3, 3, 5, 6, 9, 4, 3, 6};
    IntStream stream = Arrays.stream(numbers);

    String[] animals = {"cats", "dog", "ox", "bats", "horses", "mule"};

    Stream<String> animalStream = Arrays.stream(animals);

    stream.sorted().
        forEach(n -> System.out.print(n + " "));

    System.out.println();

    animalStream.forEach(n -> System.out.print(n + " "));

    List<Person> group = new ArrayList<Person>() {{
      add(new Person("c", 1));
      add(new Person("a", 3));
      add(new Person("b", 2));
    }};

    System.out.println("sort by age:");
    group.stream().sorted(Comparator.comparingInt(Person::getAge)).forEach(e -> System.out.println(e));
    System.out.println("sort by name:");
    group.stream().sorted(Comparator.comparing(Person::getName)).forEach(e -> System.out.println(e));
  }

  static class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public int getAge() {
      return age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setAge(int age) {
      this.age = age;
    }

    @Override
    public String toString() {
      return "Person{" +
          "name='" + name + '\'' +
          ", age=" + age +
          '}';
    }
  }
}
