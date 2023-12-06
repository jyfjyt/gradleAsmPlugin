package com.jyf.plugin.apktool;

import com.jyf.plugin.util.PluginLog;

public class CopyRunner implements IRunner{

    private final String mOutputFile;

    public CopyRunner(String out) {
        mOutputFile=out;
    }

    @Override
    public void run(){
        PluginLog.println("==============> com.deepblue.runner.CopyRunner Starting");
        PluginLog.println("==============> mOutputFile:" + mOutputFile);

//        boolean flag=copyFile(mOutputFile,"C:\\Users\\jinyf\\Desktop\\"+"123.apk");
//        if (flag){
//            System.out.println("``````````````` com.deepblue.runner.CopyRunner Success");
//        }else{
//            System.out.println("!!!!!!!!!!!!!!! com.deepblue.runner.CopyRunner Failed");
//        }
        PluginLog.println("``````````````` com.deepblue.Test Success");
    }

}
