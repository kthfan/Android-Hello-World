package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log

class MainActivity2 : AppCompatActivity() {
    protected  val logTag = "MainActivity2.Class"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Log.v(logTag, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.v(logTag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v(logTag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v(logTag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v(logTag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(logTag, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(logTag, "onRestart")
    }
}