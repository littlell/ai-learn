package com.demo.jvm.basics.jvm.classloader;

public class Demo {

  public void main(String[] args) {
    {
      int a = 0;
      System.out.println(a);
    }
    {
      int b = 0;
      System.out.println(b);
    }
    int i = 2;
    int b = ++i;
    new Demo().p(i);
  }

  public void p(Object i) {

  }

}
