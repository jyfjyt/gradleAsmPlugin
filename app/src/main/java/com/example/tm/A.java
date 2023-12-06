package com.example.tm;

import android.util.Log;

public class A {
    public void performTask() {
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.i("123123","A's task is running. performTask");
//        }).start();

        new Thread(new MyRunnable("AAA") {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("123123", "------------myname 11:" + getName());
                Log.i("123123", "AAA's task is running. performTask");
            }
        }).start();
    }
}
