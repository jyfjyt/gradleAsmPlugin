package com.jyf.plugin.asmlogger;

import com.android.build.api.instrumentation.AsmClassVisitorFactory;
import com.android.build.api.instrumentation.ClassContext;
import com.android.build.api.instrumentation.ClassData;
import com.android.build.api.instrumentation.InstrumentationParameters;
import com.jyf.plugin.Config;
import com.jyf.plugin.util.PluginLog;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public abstract class AsmLogTransform implements AsmClassVisitorFactory<InstrumentationParameters.None> {


    @NotNull
    @Override
    public ClassVisitor createClassVisitor(@NotNull ClassContext classContext, @NotNull ClassVisitor classVisitor) {
        String className = classContext.getCurrentClassData().getClassName();
        PluginLog.println("className:"+className);
        return new ClassVisitor(Opcodes.ASM5,classVisitor) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor oldMethodVisitor=super.visitMethod(access,name,descriptor,signature,exceptions);
                return AsmLogMethodVisitorCreator.getInstance().obtion(className, oldMethodVisitor, access, name, descriptor);
            }
        };
    }

    @Override
    public boolean isInstrumentable(@NotNull ClassData classData) {
        String className=classData.getClassName();

        boolean flag=className.startsWith(Config.PACKAGE_NAME)
                && !className.endsWith(".R")
                && !className.endsWith(".BR")
                && !className.contains(".databinding.")
                && !className.contains(".R$")
                && !className.contains("BuildConfig");

        return flag&&!className.contains("com.example.tm.asm");
    }
}
