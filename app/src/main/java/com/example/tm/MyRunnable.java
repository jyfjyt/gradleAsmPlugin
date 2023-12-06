package com.example.tm;

import android.util.Log;

public class MyRunnable implements Runnable{

    private String name;

    public String getName() {
        return name;
    }

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Log.i("123123","name:"+name);
    }
}
