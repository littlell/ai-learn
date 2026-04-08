package com.demo.jvm.basics.bytecode;

import com.demo.jvm.basics.bytecode.bean.User;
import com.demo.jvm.basics.bytecode.util.ClassFileUtil;

import org.apache.commons.codec.binary.Hex;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.V1_5;

public class RemoveDebugAdapter extends ClassVisitor {

  public RemoveDebugAdapter(int api, ClassVisitor classVisitor) {
    super(api, classVisitor);
  }

  @Override
  public void visitSource(String source, String debug) {
  }

  @Override
  public void visitInnerClass(String name, String outerName, String innerName, int access) {
  }

  @Override
  public void visitOuterClass(String owner, String name, String descriptor) {
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
    super.visit(version, access, name + "RemoveDebug", signature, superName, interfaces);
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {

    ClassWriter classWriter = new ClassWriter(0);

    TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out));

    RemoveDebugAdapter visitor = new RemoveDebugAdapter(ASM4, traceClassVisitor);

    ClassReader reader = new ClassReader(Type.getInternalName(User.class));

    reader.accept(visitor, 0);

    byte[] b = classWriter.toByteArray();

    ClassFileUtil.writeClassFile("com/demo/jvm/basics/bytecode/bean/UserRemoveDebug", b);
  }
}
