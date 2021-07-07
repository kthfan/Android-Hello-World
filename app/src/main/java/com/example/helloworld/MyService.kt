package com.example.helloworld

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log


class MyService : Service() {
    protected  val logTag = "MyService.Class"
    protected lateinit var mediaPlayer: MediaPlayer
    protected lateinit var myBinder : LocalBinder

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): MyService = this@MyService
    }

    override fun onCreate() {
        Log.e(logTag, "onCreate")
        super.onCreate()
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music1)
        mediaPlayer.start()
    }

    override fun onDestroy() {
        Log.e(logTag, "onDestroy")
        mediaPlayer.stop()
        mediaPlayer.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.e(logTag, "onBind")
        myBinder = LocalBinder()
        return myBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(logTag, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(logTag, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    fun myFunc(){
        Log.e(logTag, "myFunc")
    }
}