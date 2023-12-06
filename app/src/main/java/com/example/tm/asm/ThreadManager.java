package com.example.tm.asm;

import android.util.Log;

import com.example.tm.MyRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private ThreadManager() {}

    public static void execute(Runnable task) {
        if (task instanceof MyRunnable){
            //这里其实是走不进去的
            Log.i("123123-223223","--------------execute myrunnable:"+((MyRunnable) task).getName());
        }else{
            Log.i("123123-223223","execute");
        }

        executor.submit(task);
    }

}