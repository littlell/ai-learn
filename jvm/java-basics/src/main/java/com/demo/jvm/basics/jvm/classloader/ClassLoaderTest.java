package com.demo.jvm.basics.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public class ClassLoaderTest {

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

    // 匿名内部类
    ClassLoader loader = new ClassLoader() {
      @Override
      public Class<?> loadClass(String name) throws ClassNotFoundException {

        // 不装载匿名内部类
        if (!name.equals("com.demo.classloader.ClassLoaderTest")) {
          return super.loadClass(name);
        }

        String className = name.substring(name.lastIndexOf(".") + 1);
        System.out.println(className);

        try {
          // getResource时当前目录为class文件所在的目录
          InputStream fileInputStream = getClass().getResourceAsStream(className + ".class");
          byte[] classBytes = fileInputStream.readAllBytes();
          return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
          e.printStackTrace();
        }

        return super.loadClass(name);
      }
    };

    Object classLoaderTest = loader
        .loadClass("com.demo.classloader.ClassLoaderTest").getDeclaredConstructor()
        .newInstance();

    System.out.println(classLoaderTest.getClass());

    System.out.println(classLoaderTest instanceof com.demo.jvm.basics.jvm.classloader.ClassLoaderTest);

    System.out.println(ClassLoaderTest.class.getClassLoader());

    System.out.println(classLoaderTest.getClass().getClassLoader());
  }

}
