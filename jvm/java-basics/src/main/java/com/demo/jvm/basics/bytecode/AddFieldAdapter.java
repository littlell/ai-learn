package com.demo.jvm.basics.bytecode;

import com.demo.jvm.basics.bytecode.bean.User;
import com.demo.jvm.basics.bytecode.util.ClassFileUtil;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ASM4;

public class AddFieldAdapter extends ClassVisitor {

  private int fAcc;

  private String fName;

  private String fDesc;

  private boolean isFieldPresent;

  public AddFieldAdapter(int api, ClassVisitor classVisitor, int fAcc, String fName, String fDesc) {
    super(api, classVisitor);
    this.fAcc = fAcc;
    this.fName = fName;
    this.fDesc = fDesc;
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
    super.visit(version, access, name + "AddField", signature, superName, interfaces);
  }

  @Override
  public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
    if (name.equals(fName)) {
      isFieldPresent = true;
    }
    return super.visitField(access, name, descriptor, signature, value);
  }

  @Override
  public void visitEnd() {
    if (!isFieldPresent) {
      FieldVisitor fv = super.visitField(fAcc, fName, fDesc, null, null);
    }
    super.visitEnd();
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {

    ClassWriter classWriter = new ClassWriter(0);

    TraceClassVisitor traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out));

    CheckClassAdapter cv = new CheckClassAdapter(traceClassVisitor);

    AddFieldAdapter visitor = new AddFieldAdapter(ASM4, cv, ACC_PRIVATE, "newField", Type.getType(String.class).getDescriptor());

    ClassReader reader = new ClassReader(Type.getInternalName(User.class));

    reader.accept(visitor, 0);

    byte[] b = classWriter.toByteArray();

    ClassFileUtil.writeClassFile("com/demo/jvm/basics/bytecode/bean/UserAddField", b);
  }
}
