package com.demo.jvm.basics.jvm.engine;

/**
 * 演示单分派和多分派 静态分派， 选择目标方法的依据有两点， 一是静态类型是Father还是Son， 二是参数是QQ还是360
 */
public class Dispatch {
  static class QQ {
  }

  static class _360 {
  }

  public static class Father {
    public void hardChoice(QQ arg) {
      System.out.println("Father choose qq");
    }

    public void hardChoice(_360 arg) {
      System.out.println("Father choose 360");
    }
  }

  public static class Son extends Father {
    public void hardChoice(QQ arg) {
      System.out.println("Son choose qq");
    }

    public void hardChoice(_360 arg) {
      System.out.println("Son choose 360");
    }
  }

  public static void main(String[] args) {

    Father father = new Father();
    Father son = new Son();
    father.hardChoice(new _360());
    son.hardChoice(new QQ());
  }
}
