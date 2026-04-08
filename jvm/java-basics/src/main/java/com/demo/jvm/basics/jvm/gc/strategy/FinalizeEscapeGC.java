package com.demo.jvm.basics.jvm.gc.strategy;

public class FinalizeEscapeGC {

  public static FinalizeEscapeGC SAVE_HOOK = null;

  public void isAlive() {
    System.out.println("yes, i am still alive :)");
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("finalize method executed!");
    // 重新构造到GCRoots的引用链
    FinalizeEscapeGC.SAVE_HOOK = this;
  }

  public static void main(String[] args) throws InterruptedException {
    SAVE_HOOK = new FinalizeEscapeGC();

    // 对象第一次成功拯救自己
    SAVE_HOOK = null;
    // 堆中的FinalizeEscapeGC对象已经没有到GCRoots的引用链
    System.gc();
    // 堆中的FinalizeEscapeGC对象没有被调用过finalize方法， 将进入F-QUEUE等待执行Finalize方法
    // 因为Finalizer方法优先级很低， 暂停0.5秒
    Thread.sleep(500);

    if (SAVE_HOOK != null) {
      SAVE_HOOK.isAlive();
    } else {
      System.out.println("no, i am dead :(");
    }

    SAVE_HOOK = null;
    // 堆中的FinalizeEscapeGC对象已经没有到GCRoots的引用链
    System.gc();
    // 因为Finalizer方法优先级很低， 暂停0.5秒， 以等待他
    Thread.sleep(500);

    if (SAVE_HOOK != null) {
      SAVE_HOOK.isAlive();
    } else {
      System.out.println("no, i am dead :(");
    }
  }
}
