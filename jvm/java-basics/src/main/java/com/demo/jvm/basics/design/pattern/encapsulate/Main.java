package com.demo.jvm.basics.design.pattern.encapsulate;

import java.util.Arrays;

public class Main {
  /** Created by littlell on 2017/3/12. */
  public static void main(String[] args) {
    User user1 = new User();
    user1.setName("user1");
    user1.setAge(1);
    User user2 = new User();
    user2.setName("user2");
    user2.setAge(5);
    User user3 = new User();
    user3.setName("user3");
    user3.setAge(2);

    User[] users = new User[] {user1, user2, user3};

    System.out.println(Arrays.toString(users));

    Arrays.sort(users);

    System.out.println(Arrays.toString(users));
  }
}
