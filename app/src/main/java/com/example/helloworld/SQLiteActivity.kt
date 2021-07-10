package com.example.helloworld

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView


class SQLiteActivity : AppCompatActivity() {

    val TAG = "SQLiteActivity.class"
    val STATE_ERROR = "error"
    val STATE_WARN = "warn"
    val STATE_INFO = "info"
    val STATE_LOG = "log"

    protected lateinit var sqlQueryTable : TableLayout
    protected lateinit var db : SQLiteDatabase
    protected lateinit var sqlExecEdit : EditText
    protected lateinit var sqlQueryEdit : EditText
    protected lateinit var sqlStateText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        sqlQueryTable = findViewById(R.id.sqlQueryTable)
        sqlExecEdit = findViewById(R.id.sqlExecEdit)
        sqlQueryEdit = findViewById(R.id.sqlQueryEdit)
        sqlStateText = findViewById(R.id.sqlStateText)

        db = openOrCreateDatabase("test.db", MODE_PRIVATE, null)
    }

    protected fun execSQL(cmd : String){
        try {
            db.execSQL(cmd)
            setState("Command '$cmd' execute success.", STATE_INFO)
        }catch (e : SQLiteException){
            setState(e.message, STATE_ERROR)
        }
    }

    protected fun rawQuery(cmd : String): Array<Array<Any?>?> {
        val cursor : Cursor = db.rawQuery(cmd, null)
        val nrow = cursor.count
        val result = arrayOfNulls<Array<Any?>>(nrow + 1)
        result[0] = cursor.columnNames as Array<Any?>
        for (i in 0 until nrow){
            cursor.moveToNext()
            val ncol = cursor.columnCount
            val rowData = arrayOfNulls<Any?>(ncol)
            for (j in 0 until ncol){
                val type = cursor.getType(j)
                var data : Any? = null
                when(type){
                    Cursor.FIELD_TYPE_FLOAT -> data = cursor.getFloat(j)
                    Cursor.FIELD_TYPE_BLOB -> data = cursor.getBlob(j)
                    Cursor.FIELD_TYPE_INTEGER -> data = cursor.getInt(j)
                    Cursor.FIELD_TYPE_NULL -> data = null
                    Cursor.FIELD_TYPE_STRING -> data = cursor.getString(j)
                }
                rowData[j] = data
            }
            result[i + 1] = rowData
        }
        return result
    }

    protected fun createRow(rowData : Array<Any?>?): TableRow? {
        if(rowData == null) return null
        val tableRow : TableRow = TableRow(this)
        for(i in rowData.indices){
            val textView : TextView = TextView(this)
            textView.setPadding(10, 0, 10, 0)
            textView.text = "${rowData?.get(i).toString()}"
            textView.setTextIsSelectable(true)
            tableRow.addView(textView)
        }
        return tableRow
    }

    protected fun buildTable(tableData : Array<Array<Any?>?>){
        sqlQueryTable.removeAllViews()

        for(i in tableData.indices){
            val tableRow = createRow(tableData[i])
            sqlQueryTable.addView(tableRow)
        }
    }

    protected fun setState(state: String?, type : String){
        sqlStateText.text = state ?: "empty state"
        when(type){
            STATE_ERROR -> sqlStateText.setTextColor(Color.RED)
            STATE_WARN -> sqlStateText.setTextColor(Color.YELLOW)
            STATE_INFO -> sqlStateText.setTextColor(Color.BLUE)
            STATE_LOG -> sqlStateText.setTextColor(Color.BLACK)
            else -> sqlStateText.setTextColor(Color.BLACK)
        }
    }

    fun onExecBnClick(view : View){
        execSQL(sqlExecEdit.text.toString())
    }

    fun onQueryBnClick(view : View){
        try {
            val cmd = sqlQueryEdit.text.toString()
            val tableData = rawQuery(cmd)
            buildTable(tableData)
            setState("Command '$cmd' execute success.", STATE_INFO)
        }catch (e : SQLiteException){
            setState(e.message, STATE_ERROR)
        }
    }
}