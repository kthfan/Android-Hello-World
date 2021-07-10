package com.example.helloworld

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SharedPrefActivity : AppCompatActivity() {
    protected lateinit var mPref : SharedPreferences
    protected lateinit var prefPutKeyEdit : EditText
    protected lateinit var prefPutVaLEdit : EditText
    protected lateinit var prefGetKeyEdit : EditText
    protected lateinit var prefGetText : TextView
    protected lateinit var prefDelKeyEdit : TextView
    protected lateinit var prefListItems : ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pref)

        mPref = getSharedPreferences("test_pref", MODE_PRIVATE)
        prefPutKeyEdit = findViewById(R.id.prefPutKeyEdit)
        prefPutVaLEdit = findViewById(R.id.prefPutVaLEdit)
        prefGetKeyEdit = findViewById(R.id.prefGetKeyEdit)
        prefGetText = findViewById(R.id.prefGetText)
        prefDelKeyEdit = findViewById(R.id.prefDelKeyEdit)
        prefListItems = findViewById(R.id.prefListItems)
        updateList()
    }

    private fun updateList(){
        val _list = mutableListOf<String>()
        val allEntries: Map<String, *> = mPref.getAll()
        for ((key, value) in allEntries) {
            _list.add("$key : ${value.toString()}")
        }
        val adapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, _list)
        prefListItems.adapter = adapter
    }

    fun putPref(view: View){
        val key : String = prefPutKeyEdit.text.toString()
        val value : String = prefPutVaLEdit.text.toString()
        mPref
            .edit()
            .putString(key, value)
            .commit()
        updateList()
    }

    fun getPref(view: View){
        val key : String = prefGetKeyEdit.text.toString()
        val value = mPref.getString(key, "")
        prefGetText.text = value
        updateList()
    }

    fun removePref(view: View){
        val key : String = prefDelKeyEdit.text.toString()
        mPref.edit().remove(key).commit()
        updateList()
    }

    fun clearPref(view: View){
        mPref.edit().clear().commit()
        updateList()
    }

}