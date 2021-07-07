package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.EditText
import android.widget.TextView

class IntentActivity : AppCompatActivity() {
    protected lateinit var intentResultText : TextView
    protected lateinit var op1Edit : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        intentResultText = findViewById(R.id.intentResultText)
        op1Edit = findViewById(R.id.op1Edit)
    }
    fun onWebPageBnClick(view : View){
        val intent = Intent()
        intent.setAction(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("https://www.google.com"))
        startActivity(intent)
    }
    fun onMailBnClick(view : View){
        val intent = Intent()
        intent.setAction(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("mailto:3999932@gmail.com"))
        startActivity(intent)
    }
    fun onPhoneBnClick(view : View){
        val intent = Intent()
        intent.setData(Uri.parse("tel:09625848"))
        intent.putExtra(Intent.EXTRA_CC, arrayOf<String>("s107053140@nchu.edu.tw"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject for test")
        intent.putExtra(Intent.EXTRA_TEXT, "text for test")
        startActivity(intent)
    }
    fun onGoogleMapBnClick(view : View){
        val intent = Intent()
        intent.setAction(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("geo:25.033493,121564101"))
        startActivity(intent)
    }
    fun onCalcBnClick(view: View){
        val intent : Intent = Intent(this, CalcActivity::class.java)
        val op1 = op1Edit.text.toString().toFloat()
        intent.putExtra("val", op1)
        startActivityForResult(intent, 100)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = data?.getFloatExtra("result", 0f)
        intentResultText.text = "request code: ${requestCode},\n result code: ${resultCode},\n result: ${result}"
        super.onActivityResult(requestCode, resultCode, data)
    }
}