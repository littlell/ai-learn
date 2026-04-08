package com.demo.jvm.basics.jvm.gc.oom;

/**
 * 栈溢出 虚拟机在扩展栈时无法申请到足够的内存空间， 则抛出OutOfMemoryError -Xss2M -Xlog:gc
 */
public class JavaVMStackOOM {

  private void dontStop() {
    while (true) {
    }
  }

  public void stackLeakByThread() {
    while (true) {
      Thread thread = new Thread(() -> dontStop());
      thread.start();
    }
  }

  public static void main(String[] args) {
    JavaVMStackOOM oom = new JavaVMStackOOM();

    oom.stackLeakByThread();
  }
}
