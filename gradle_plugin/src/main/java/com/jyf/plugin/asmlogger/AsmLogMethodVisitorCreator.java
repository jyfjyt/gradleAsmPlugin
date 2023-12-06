
package com.jyf.plugin.asmlogger;


import org.objectweb.asm.MethodVisitor;

public class AsmLogMethodVisitorCreator {

    private AsmLogMethodVisitorCreator(){}

    private static final AsmLogMethodVisitorCreator mInstance=new AsmLogMethodVisitorCreator();


    public static AsmLogMethodVisitorCreator getInstance(){
        return mInstance;
    }

    public  MethodVisitor obtion(String className, MethodVisitor oldMethodVisitor, int access, String name, String descriptor) {
        return new AsmLogMethodVisitor(className, oldMethodVisitor, access, name, descriptor);
    }
}
