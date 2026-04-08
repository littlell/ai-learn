package com.demo.jvm.basics.jvm.gc.oom;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 方法区溢出 -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M -Xlog:gc
 */
public class JavaMethodAreaOOM {
  public static void main(String[] args) {

    while (true) {
      Enhancer enhancer = new Enhancer();
      enhancer.setSuperclass(OOMObject.class);
      enhancer.setUseCache(false);
      enhancer.setCallback(new MethodInterceptor() {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
          return methodProxy.invokeSuper(o, objects);
        }
      });

      enhancer.create();
    }
  }

  static class OOMObject {

  }
}
