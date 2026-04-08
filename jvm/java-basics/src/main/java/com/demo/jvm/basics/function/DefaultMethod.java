package com.demo.jvm.basics.function;

public class DefaultMethod {
  public interface Computable {
    int compute();

    default int doubleNumber(int num) {
      return 2 * num;
    }

    default int negateNumber(int num) {
      return -1 * num;
    }
  }

  public static class ComputeImpl implements Computable {
    @Override
    public int compute() {
      return 0;
    }
  }

  public static void main(String[] args) {
    ComputeImpl compute = new ComputeImpl();

    System.out.println(compute.compute());

    System.out.println(compute.doubleNumber(1));

    System.out.println(compute.negateNumber(1));
  }
}
