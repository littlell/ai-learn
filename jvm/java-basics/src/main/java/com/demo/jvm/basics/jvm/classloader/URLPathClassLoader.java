package com.demo.jvm.basics.jvm.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class URLPathClassLoader extends URLClassLoader {

  private String packageName = "com.demo.classloader";

  public URLPathClassLoader(URL[] urls) {
    super(urls);
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    return super.findClass(name);
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
}
