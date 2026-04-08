package com.demo.jvm.basics.jvm.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PathClassLoaderMain extends ClassLoader {

  public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
    // 使用当前工程 target/classes（在 jvm/java-basics 下运行时的相对路径）
    PathClassLoader pathClassLoader = new PathClassLoader("target/classes");

    Class<?> aClass = pathClassLoader.loadClass("com.demo.jvm.basics.jvm.classloader.Demo");

    System.out.println("classLoader: " + aClass.getClassLoader());

    Object object = aClass.getDeclaredConstructor().newInstance();

    Method method = aClass.getMethod("print");

    method.invoke(object);
  }
}
