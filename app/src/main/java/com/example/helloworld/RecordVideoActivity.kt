package com.example.helloworld

import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RecordVideoActivity : AppCompatActivity() {

    protected lateinit var record : MediaRecorder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_video)


    }

    protected fun startRecording(){
        record = MediaRecorder()
        record
    }

    fun onBnClick(view: View){
        when(view.id){
            R.id.recordVideoRecord1Bn -> {}
            R.id.recordVideoStop1Bn -> {}
        }
    }
}