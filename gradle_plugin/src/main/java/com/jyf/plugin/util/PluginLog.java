package com.jyf.plugin.util;

import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.LogLevel;

public class PluginLog {

//    private static Logger mLogger;

    private PluginLog() {
    }

    public static void init(Project project) {
//        mLogger = project.getRootProject().getLogger();
    }

    public static void println(String s) {
        System.out.println(s);
//        mLogger.log(LogLevel.INFO, s);
    }

    public static void pirntAllTask(String tag, Project project) {
        project.getTasks().forEach(
                it -> {
                    String taskName = it.getName();
                    println(tag + ":" + taskName);
                }
        );
        println("=======================\n\n=======================\n");
    }
}
