package com.demo.jvm.basics.bytecode.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClassFileUtil {

  public static void writeClassFile(String cName, byte[] cBytes) {

    String targetOutputClassPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    File f = new File(targetOutputClassPath + cName + ".class");

    try {
      FileOutputStream fos = new FileOutputStream(f);

      fos.write(cBytes);

      fos.close();
    } catch (Exception e) {
    }
  }

  public static byte[] readClassFile(String cName) {

    String targetOutputClassPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    File f = new File(targetOutputClassPath + cName + ".class");

    try {
      FileInputStream fis = new FileInputStream(f);

      byte[] b = fis.readAllBytes();
      fis.close();

      return b;
    } catch (Exception e) {
      return null;
    }
  }
}
