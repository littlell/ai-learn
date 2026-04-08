package com.demo.jvm.basics.bytecode;

import com.demo.jvm.basics.bytecode.bean.User;

import static org.objectweb.asm.Opcodes.ASM4;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Type;

public class ClassReaderDemo extends ClassVisitor {

  public ClassReaderDemo(int api) {
    super(api);
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName,
                    String[] interfaces) {
    System.out.println("Class[" + name + "] extends [" + superName + "]");
  }

  @Override
  public FieldVisitor visitField(int access, String name, String descriptor, String signature,
                                 Object value) {
    System.out.println("Field[" + name + "]");
    return null;
  }

  @Override
  public void visitEnd() {
    System.out.println("End come.");
  }

  public static void main(String[] args) throws IOException {
    ClassReaderDemo classPrinter = new ClassReaderDemo(ASM4);

    ClassReader reader = new ClassReader(Type.getInternalName(User.class));
    reader.accept(classPrinter, 0);
  }
}
