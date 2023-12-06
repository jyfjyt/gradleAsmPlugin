
package com.jyf.plugin.threadmg;


import org.objectweb.asm.MethodVisitor;

public class ThreadMgMethodVisitorCreator {

    private ThreadMgMethodVisitorCreator(){}

    private static final ThreadMgMethodVisitorCreator mInstance=new ThreadMgMethodVisitorCreator();


    public static ThreadMgMethodVisitorCreator getInstance(){
        return mInstance;
    }

    public  MethodVisitor obtion(String className, MethodVisitor oldMethodVisitor, int access, String name, String descriptor) {
        return new ThreadMgMethodVisitor(className, oldMethodVisitor, access, name, descriptor);
    }
}
