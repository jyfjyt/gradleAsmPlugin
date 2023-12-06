package com.example.tm;

import android.util.Log;

public class B {
    public void performTask1() {
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.i("123123","B's first task is running. performTask1");
//        }).start();

        new Thread(new MyRunnable("BBB"){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("123123","------------myname 21:"+getName());
                Log.i("123123","BBB's task is running. performTask 1");
            }
        }).start();
    }

    public void performTask2() {
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.i("123123","B's second task is running. performTask2");
//        }).start();

        new Thread(new MyRunnable("BBB"){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("123123","------------myname 22:"+getName());
                Log.i("123123","BBB's task is running. performTask 2");
            }
        }).start();
    }
}