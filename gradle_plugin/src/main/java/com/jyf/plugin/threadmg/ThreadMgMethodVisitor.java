package com.jyf.plugin.threadmg;

import com.jyf.plugin.util.PluginLog;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;


public class ThreadMgMethodVisitor extends AdviceAdapter {

    private final String mClassName;

    public ThreadMgMethodVisitor(String className, MethodVisitor oldMethodVisitor, int access, String name, String descriptor) {
        super(Opcodes.ASM5, oldMethodVisitor, access, name, descriptor);
        mClassName = className;
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
    public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {


        if (opcodeAndSource == Opcodes.INVOKEVIRTUAL && "java/lang/Thread".equals(owner) && "start".equals(name)
        ) {
            PluginLog.println("owner:"+owner+"----name:"+name+"----descriptor:"+descriptor);
            myFunction();
        } else {
            super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
        }
    }

    private void myFunction() {
        // 替换Thread.start()为ThreadManager.execute()
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/example/tm/asm/ThreadManager", "execute", "(Ljava/lang/Runnable;)V", false);
    }
}
