package com.demo.jvm.basics.jvm.classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class URLPathClassLoaderMain {
  public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    // 使用当前工程 target/classes（在 jvm/java-basics 下运行时的相对路径）
    File classFileDir = new File("target/classes");
    URL classFileDirUrl = classFileDir.toURI().toURL();

    URL[] urls = new URL[]{classFileDirUrl};

    URLPathClassLoader urlPathClassLoader = new URLPathClassLoader(urls);

    Class<?> aClass = urlPathClassLoader.loadClass("com.demo.jvm.basics.jvm.classloader.Demo");

    System.out.println("classLoader: " + aClass.getClassLoader());

    Object object = aClass.getDeclaredConstructor().newInstance();

    Method method = aClass.getMethod("print");

    method.invoke(object);
  }
}
