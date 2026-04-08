package com.demo.jvm.basics.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursiveTypeBound {

  public static void main(String[] args) {
    List<Person> personList = new ArrayList<>();

    personList.add(new Person(1));
    personList.add(new Person(2));
    personList.add(new Person(3));

    System.out.println(findMax(personList));

    List<Member> memberList = new ArrayList<>();

    memberList.add(new Member(4));
    memberList.add(new Member(5));
    memberList.add(new Member(6));

    System.out.println(findMax(memberList));

    personList.addAll(memberList);
    System.out.println(findMax(personList));
  }

  // Comparable Comparator都是消费者
  // 限定通配符增加方法的灵活性, Member未实现Comparable接口, 同样适用该方法
  static <T extends Comparable<? super T>> T findMax(List<? extends T> targetList) {
    Iterator<? extends T> iterator = targetList.iterator();
    T t1 = iterator.next();

    while (iterator.hasNext()) {
      T t2 = iterator.next();
      if (t1.compareTo(t2) == 0) {
        t1 = t2;
      }
    }

    return t1;
  }

  static class Person implements Comparable<Person> {

    private int age;

    public Person(int age) {
      this.age = age;
    }

    public int getAge() {
      return age;
    }

    @Override
    public int compareTo(Person person) {
      if (age > person.getAge()) {
        return 1;
      }
      return 0;
    }

    @Override
    public String toString() {
      return "Person{" +
          "age=" + age +
          '}';
    }
  }

  static class Member extends Person {

    public Member(int age) {
      super(age);
    }
  }

}
