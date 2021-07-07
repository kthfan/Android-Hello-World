package com.example.helloworld

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.os.Binder

class ServiceActivity : AppCompatActivity() {

    protected lateinit var serviceConn : ServiceConnection
    protected var myService: MyService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        serviceConn = object :  ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as MyService.LocalBinder
                myService = binder.getService()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                myService = null
            }
        }
    }
    fun bnClick(view : View){
        when(view.id){
            R.id.serviceStart -> startService(Intent(this, MyService::class.java))
            R.id.serviceEnd -> stopService(Intent(this, MyService::class.java))
            R.id.bindService -> bindService(Intent(this, MyService::class.java), serviceConn, BIND_AUTO_CREATE)
            R.id.unbindService -> unbindService(serviceConn)
            R.id.callServiceFunc -> myService?.myFunc()
        }
    }
}