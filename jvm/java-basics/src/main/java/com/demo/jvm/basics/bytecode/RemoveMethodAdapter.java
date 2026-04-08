package com.demo.jvm.basics.bytecode;

import com.demo.jvm.basics.bytecode.bean.User;
import com.demo.jvm.basics.bytecode.util.ClassFileUtil;

import org.apache.commons.codec.binary.Hex;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

import static org.objectweb.asm.Opcodes.ASM4;

public class RemoveMethodAdapter extends ClassVisitor {

  private String mName;
  private String mDesc;

  public RemoveMethodAdapter(int api, ClassVisitor classVisitor, String mName, String mDesc) {
    super(api, classVisitor);
    this.mName = mName;
    this.mDesc = mDesc;
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
    super.visit(version, access, name + "RemoveMethod", signature, superName, interfaces);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

    if (name.equals(mName) && descriptor.equals(mDesc)) {
      return null;
    }

    return super.visitMethod(access, name, descriptor, signature, exceptions);
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {

    ClassWriter classWriter = new ClassWriter(0);

    TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out));

    RemoveMethodAdapter visitor = new RemoveMethodAdapter(ASM4, traceClassVisitor, "hi", "()V");

    ClassReader reader = new ClassReader(Type.getInternalName(User.class));

    reader.accept(visitor, 0);

    byte[] b = classWriter.toByteArray();

    ClassFileUtil.writeClassFile("com/demo/jvm/basics/bytecode/bean/UserRemoveMethod", b);
  }
}
