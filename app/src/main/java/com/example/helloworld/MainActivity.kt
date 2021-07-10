package com.example.helloworld

import java.util.Calendar


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.TimePickerDialog
import android.app.DatePickerDialog
import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.content.DialogInterface
import android.content.Intent


class MainActivity : AppCompatActivity() {
    lateinit var myToast : Toast
    lateinit var backgorundText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        backgorundText = findViewById(R.id.textView)
        myToast = Toast.makeText(this, "empty toast", Toast.LENGTH_LONG)
    }
    fun testToast(view : View){
        if(view.id == R.id.bn1)       myToast.setText("this is bn1")
        else if(view.id == R.id.bn2)  myToast.setText("這是bn2")
        else{
            myToast.cancel()
            return
        }
        myToast.show()
    }
    fun testAlert(view : View){

        val builder = AlertDialog.Builder(this)
            .setMessage("test string")
            .setCancelable(true)
            .setTitle("title")

        builder.setPositiveButton("yes"){
            dialog : DialogInterface, option : Int ->
                backgorundText.text = "Alert ok"
        }
        builder.setNegativeButton("no"){
            dialog : DialogInterface, option : Int ->
                backgorundText.text = "Alert no"
        }
        builder.setNeutralButton("whatever"){
            dialog : DialogInterface, option : Int ->
                backgorundText.text = "Alert I don't care"
        }
        builder.show()
    }
    fun testDateTime(view : View){
        val now = Calendar.getInstance()
        val year = now.get(Calendar.YEAR)
        val month = now.get(Calendar.MONTH)
        val day = now.get(Calendar.DAY_OF_MONTH)
        val hour = now.get(Calendar.HOUR_OF_DAY)
        val minute = now.get(Calendar.MINUTE)
        val listener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

        }
        when(view.id){
            R.id.alertDate -> DatePickerDialog(this, null, year, month, day).show()
            R.id.alertTime -> TimePickerDialog(this, null, hour, minute, true).show()
        }
    }
    protected fun _createActivity( cls :  Class<*>){
        val intent : Intent = Intent(this, cls)
        startActivity(intent)
    }
    fun testNewActivity(view : View){
        _createActivity(MainActivity2::class.java)
    }


    fun testWidget(view: View){
        _createActivity(WigetActivity::class.java)
    }

    fun testIntent(view: View){
        _createActivity(IntentActivity::class.java)
    }
    fun testService(view: View){
        _createActivity(ServiceActivity::class.java)
    }
    fun testPhotograph(view: View){
        _createActivity(PhotographActivity::class.java)
    }
    fun testRecordVideo(view: View){
        _createActivity(RecordVideoActivity::class.java)
    }
    fun testSharedPref(view: View) {
        _createActivity(SharedPrefActivity::class.java)
    }
    fun testSensor(view : View){
        _createActivity(SensorActivity::class.java)
    }
    fun testGPS(view : View){
        _createActivity(GpsActivity::class.java)
    }
    fun testSQLite(view : View){
        _createActivity(SQLiteActivity::class.java)
    }
}