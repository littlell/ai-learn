package com.demo.jvm.basics.jvm.gc.oom;

/**
 * 栈溢出 线程请求的栈深度大于虚拟机所允许的最大深度 , 将抛出 StackOverflowError -Xss180k -Xlog:gc
 */
public class JavaVmStackSOF {
  private int stackLength = 1;

  public void stackLeak() {
    stackLength++;

    stackLeak();
  }

  public static void main(String[] args) {

    JavaVmStackSOF oom = new JavaVmStackSOF();

    // 只能捕获Throwable， 因为StackOverflowError集成Error， 不是Exception
    try {
      oom.stackLeak();
    } catch (Throwable e) {
      System.out.println("stack length: " + oom.stackLength);
      throw e;
    }
  }
}
