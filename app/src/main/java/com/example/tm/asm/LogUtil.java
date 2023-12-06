package com.example.tm.asm;

import android.util.Log;


public class LogUtil {

    private LogUtil() {
    }

    private static long mTime;


    public static void refreshTime(String packageName,String functionName) {
        Log.i("333333", "refreshTime:" + packageName+"/"+functionName);
        mTime = System.currentTimeMillis();
    }

    public static void calFuncTime(String packageName,String functionName) {
        Log.i("333333", "path:" + packageName+"/"+functionName + "---:" + (System.currentTimeMillis() - mTime) + " ms");
    }
}
