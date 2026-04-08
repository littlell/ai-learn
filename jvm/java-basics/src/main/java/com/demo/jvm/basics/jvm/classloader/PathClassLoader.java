package com.demo.jvm.basics.jvm.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PathClassLoader extends ClassLoader {
  private String packageName = "com.demo.classloader";
  private String classPath;

  public PathClassLoader(String classPath) {
    this.classPath = classPath;
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {

    if (!name.startsWith(packageName)) {
      return super.loadClass(name);
    }

    Class<?> c = findLoadedClass(name);

    if (null != c) {
      return c;
    }

    return findClass(name);
  }

  @Override
  protected Class<?> findClass(String name) {

    Class c = findLoadedClass(name);
    if (null != c) {
      return c;
    }

    byte[] classData = new byte[0];
    try {
      classData = loadClassData(name);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return defineClass(name, classData, 0, classData.length);
  }


  private byte[] loadClassData(String name) throws IOException {
    byte[] result;

    String filePath = classPath + name.replace(".", "/") + ".class";

    FileInputStream fileInputStream = new FileInputStream(new File(filePath));

    result = fileInputStream.readAllBytes();

    return result;
  }

}
