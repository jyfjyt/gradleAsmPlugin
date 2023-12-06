package com.example.tm;

import android.util.Log;

public class C {
    public void performTask1() {
//        new Thread(() -> {
//            // 模拟耗时操作
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.i("123123","C's first task is running. performTask1");
//        }).start();

        new Thread(new MyRunnable("CCC"){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("123123","------------myname 31:"+getName());
                Log.i("123123","CCC's task is running. performTask 1");
            }
        }).start();
    }

    public void performTask2() {
//        new Thread(() -> {
//            // 模拟耗时操作
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.i("123123","C's second task is running. performTask2");
//        }).start();

        new Thread(new MyRunnable("CCC"){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("123123","------------myname 32:"+getName());
                Log.i("123123","CCC's task is running. performTask 2");
            }
        }).start();
    }

    public void performTask3() {
//        new Thread(() -> {
//            // 模拟耗时操作
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.i("123123","C's third task is running. performTask3");
//        }).start();

        new Thread(new MyRunnable("CCC"){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("123123","------------myname 33:"+getName());
                Log.i("123123","CCC's task is running. performTask 3");
            }
        }).start();
    }
}