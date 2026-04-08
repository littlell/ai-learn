package com.demo.jvm.basics.bytecode;

import com.demo.jvm.basics.bytecode.util.ClassFileUtil;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.V1_5;

public class ClassWriterDemo {

  static class StubClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
      if (name.startsWith("com.demo.")) {

        byte[] b = ClassFileUtil.readClassFile(name.replaceAll("\\.", "/"));
        return defineClass(name, b, 0, b.length);
      }
      return super.findClass(name);
    }
  }

  static byte[] makeClass() {
    ClassWriter classWriter = new ClassWriter(0);

    TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out));

    traceClassVisitor
        .visit(V1_5, ACC_PUBLIC, "com/demo/jvm/basics/bytecode/bean/Student", null, "java/lang/Object",
            null);

    traceClassVisitor.visitField(ACC_PRIVATE, "name", "Ljava/lang/String;", null, null).visitEnd();
    traceClassVisitor.visitField(ACC_PRIVATE, "age", "I", null, null).visitEnd();

    traceClassVisitor.visitEnd();

    byte[] b = classWriter.toByteArray();

    return b;
  }

  public static void main(String[] args) throws ClassNotFoundException, IOException {

    ClassFileUtil.writeClassFile("com/demo/jvm/basics/bytecode/bean/Student", makeClass());

    Class c = new StubClassLoader().findClass("com.demo.jvm.basics.bytecode.bean.Student");

    System.out.println(c.getName());
  }

}
