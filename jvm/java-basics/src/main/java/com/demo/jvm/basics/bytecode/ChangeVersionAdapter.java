package com.demo.jvm.basics.bytecode;

import com.demo.jvm.basics.bytecode.bean.User;
import com.demo.jvm.basics.bytecode.util.ClassFileUtil;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.V1_5;

public class ChangeVersionAdapter extends ClassVisitor {

  public ChangeVersionAdapter(int api, ClassVisitor classVisitor) {
    super(api, classVisitor);
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName,
                    String[] interfaces) {
    cv.visit(V1_5, access, name + "StubVersion", signature, superName, interfaces);
  }

  public static void main(String[] args) throws IOException {

    ClassWriter classWriter = new ClassWriter(0);

    TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out));

    CheckClassAdapter cv = new CheckClassAdapter(traceClassVisitor);

    ChangeVersionAdapter visitor = new ChangeVersionAdapter(ASM4, cv);

    ClassReader reader = new ClassReader(Type.getInternalName(User.class));

    reader.accept(visitor, 0);

    byte[] b = classWriter.toByteArray();

    ClassFileUtil.writeClassFile("com/demo/jvm/basics/bytecode/bean/UserStubVersion", b);
  }
}