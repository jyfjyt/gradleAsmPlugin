package com.example.tm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val a = A()
        val b = B()
        val c = C()

        a.performTask()
        b.performTask1()
        b.performTask2()
        c.performTask1()
        c.performTask2()
        c.performTask3()
    }
}