package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.EditText

class CalcActivity : AppCompatActivity() {
    protected var num1 : Float = 0f
    protected lateinit var op2Edit : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        num1 = intent.getFloatExtra("val", 0f)
        op2Edit = findViewById(R.id.op2Edit)
    }

    fun onBnClick(view : View) {

        val i = Intent()
        i.setClass(this, IntentActivity::class.java)
        val num2 = op2Edit.text.toString().toFloat()
        i.putExtra("result", num1 + num2)
        setResult(101, i)
        finish()
    }
}