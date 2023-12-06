package com.jyf.plugin.asmlogger;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;


public class AsmLogMethodVisitor extends AdviceAdapter {

    private final String mClassName;
    private final String mFunctionName;

    public AsmLogMethodVisitor(String className, MethodVisitor oldMethodVisitor, int access, String name, String descriptor) {
        super(Opcodes.ASM5, oldMethodVisitor, access, name, descriptor);
        mClassName = className;
        mFunctionName = name;
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
    }


    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
    }


    @Override
    public void visitCode() {
        super.visitCode();

        if ("run".equals(mFunctionName)) {
            // 在方法开始处插入LogUtil.refreshTime()
            mv.visitLdcInsn(mClassName);
            mv.visitLdcInsn(mFunctionName);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/example/tm/asm/LogUtil", "refreshTime", "(Ljava/lang/String;Ljava/lang/String;)V", false);
        }
    }

    @Override
    public void visitInsn(int opcode) {

        if ("run".equals(mFunctionName)) {
            // 在方法返回之前插入LogUtil.calFuncTime(packageName, methodName)
            if (
                    //IRETURN（Opcodes.IRETURN）: 返回整数。
                //LRETURN（Opcodes.LRETURN）: 返回长整数。
                //FRETURN（Opcodes.FRETURN）: 返回浮点数。
                //DRETURN（Opcodes.DRETURN）: 返回双精度浮点数。
                //ARETURN（Opcodes.ARETURN）: 返回对象引用。
                //RETURN（Opcodes.RETURN）: 用于返回void。
                    (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                    //这部分检查当前指令是否是ATHROW，即抛出异常指令。
                    || opcode == Opcodes.ATHROW) {
                mv.visitLdcInsn(mClassName);
                mv.visitLdcInsn(mFunctionName);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/example/tm/asm/LogUtil", "calFuncTime", "(Ljava/lang/String;Ljava/lang/String;)V", false);
            }
        }
        super.visitInsn(opcode);
    }

}
