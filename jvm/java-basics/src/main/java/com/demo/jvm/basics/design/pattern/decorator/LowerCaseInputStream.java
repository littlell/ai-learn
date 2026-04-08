package com.demo.jvm.basics.design.pattern.decorator;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LowerCaseInputStream extends FilterInputStream {
  /**
   * Creates a <code>FilterInputStream</code> by assigning the argument <code>in</code> to the field
   * <code>this.in</code> so as to remember it for later use.
   *
   * @param in the underlying input stream, or <code>null</code> if this instance is to be created
   *     without an underlying stream.
   */
  protected LowerCaseInputStream(InputStream in) {
    super(in);
  }

  @Override
  public int read() throws IOException {
    int c = super.read();
    return (c == -1 ? c : Character.toLowerCase(c));
  }

  @Override
  public int read(byte[] b, int off, int len) throws IOException {
    int c = super.read(b, off, len);
    for (int i = off; i < off + len; i++) {
      b[i] = (byte) Character.toLowerCase(new Integer(b[i]));
    }
    return c;
  }

  /** Created by xialei on 2017/1/2. */
  public static void main(String[] args) throws IOException {
    FileInputStream fileInputStream1 = new FileInputStream("/tmp/test.data");
    FileInputStream fileInputStream2 = new FileInputStream("/tmp/test.data");
    LowerCaseInputStream lowerCaseInputStream1 = new LowerCaseInputStream(fileInputStream1);
    LowerCaseInputStream lowerCaseInputStream2 = new LowerCaseInputStream(fileInputStream2);
    int c;
    byte[] bytes = new byte[fileInputStream2.available()];

    while ((c = lowerCaseInputStream1.read()) != -1) {
      System.out.print((char) c);
    }

    lowerCaseInputStream2.read(bytes, 0, lowerCaseInputStream2.available());

    for (byte b : bytes) {
      System.out.print((char) b);
    }

    lowerCaseInputStream1.close();
    lowerCaseInputStream2.close();
    fileInputStream1.close();
  }
}
