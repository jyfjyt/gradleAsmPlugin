package com.jyf.plugin.apktool;

import com.android.build.gradle.AppExtension;
import com.jyf.plugin.util.PluginLog;

import org.gradle.api.Project;
import org.gradle.api.Task;

public class AppExtensionRunner implements IRunner{

    private static final String BUILD_TYPE_RELEASE = "release";
    private static final String BUILD_TASK_RELEASE = "assembleRelease";

    private static final String BUILD_TYPE_DEBUG = "debug";
    private static final String BUILD_TASK_DEBUG = "assembleDebug";

    private final Project project;
    private final AppExtension appExt;

    private Callback mCallback;

    private String mReleaseOuputApkPath = null;
    private String mDebugOuputApkPath = null;

    public AppExtensionRunner(Project project, AppExtension appExt) {
        this.project =project;
        this.appExt=appExt;
    }

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void run() {
        appExt.getApplicationVariants().forEach(v -> {
            PluginLog.println("===> AndroidApplicationVariants:" + v.getBuildType().getName());
            if (v.getBuildType().getName().equals(BUILD_TYPE_RELEASE)) {
                v.getOutputs().forEach(
                        ot -> {
                            mReleaseOuputApkPath = ot.getOutputFile().toString();
                            PluginLog.println("===> ReleaseOutputFileName:" + mReleaseOuputApkPath);
                        }
                );
            }else if(v.getBuildType().getName().equals(BUILD_TYPE_DEBUG)) {
                v.getOutputs().forEach(
                        ot -> {
                            mDebugOuputApkPath = ot.getOutputFile().toString();
                            PluginLog.println("===> DebugOutputFileName:" + mDebugOuputApkPath);
                        }
                );
            }
        });

        if (mReleaseOuputApkPath!=null){
            Task dstTask = project.getTasks().findByName(BUILD_TASK_RELEASE);
            assert dstTask != null;
            dstTask.doLast(dt -> {
                new CopyRunner(mReleaseOuputApkPath).run();

                if (mCallback!=null){
                    mCallback.onEnd();
                }
            });
        }else{
            PluginLog.println("!!! mReleaseOuputApkPath == null !!!");
        }

        if (mDebugOuputApkPath!=null){
            Task dstTask = project.getTasks().findByName(BUILD_TASK_DEBUG);
            assert dstTask != null;
            dstTask.doLast(dt -> {
                if (mCallback!=null){
                    mCallback.onEnd();
                }
            });
        }else{
            PluginLog.println("!!! mDebugOuputApkPath == null !!!");
        }
    }

    public interface Callback{
        void onEnd();
    }
}
