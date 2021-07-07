package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class WigetActivity : AppCompatActivity() {

    protected val MENU_SMALL_FONT = Menu.FIRST
    protected val MENU_BIG_FONT = Menu.FIRST + 1

    protected lateinit var statusText : TextView
    protected lateinit var listenBn : Button
    protected lateinit var checkBoxJava : CheckBox
    protected lateinit var checkBoxKotlin : CheckBox
    protected lateinit var radioBlue : RadioButton
    protected lateinit var radioGreen : RadioButton
    protected lateinit var radioGroup1 : RadioGroup
    protected lateinit var imageDogCat : ImageView
    protected lateinit var seekBar1 : SeekBar
    protected lateinit var listView1 : ListView
    protected lateinit var listView2 : ListView
    protected lateinit var spinner1 : Spinner
    protected lateinit var contextMenuInvoker : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wiget)

        _initVar()
        bnClickListener()
        bnLongClickListener()
        checkBoxChangeListener()
        radioChangeListener()
        seekBarProgressListener()
        buildListView1()
        buildSpinner1()
        registerForContextMenu(contextMenuInvoker)
    }
    protected fun _initVar(){
        statusText = findViewById(R.id.statusText4Widget)
        listenBn = findViewById(R.id.ListenBn)
        checkBoxJava = findViewById(R.id.checkBoxJava)
        checkBoxKotlin = findViewById(R.id.checkBoxKotlin)
        radioBlue = findViewById(R.id.radioBlue)
        radioGreen = findViewById(R.id.radioGreen)
        radioGroup1 = findViewById(R.id.radioGroup1)
        imageDogCat = findViewById(R.id.imageDogCat)
        seekBar1 = findViewById(R.id.seekBar1)
        listView1 = findViewById(R.id.listView1)
        listView2 = findViewById(R.id.listView2)
        spinner1 = findViewById(R.id.spinner1)
        contextMenuInvoker = findViewById(R.id.contextMenuInvoker)
    }
    protected fun _setStatusText(str : String){
        statusText.text = str
    }
    protected  fun bnClickListener(){
        listenBn.setOnClickListener(){
            view : View -> _setStatusText("click by listener")
        }
    }
    protected  fun bnLongClickListener(){
        listenBn.setOnLongClickListener(){
            _setStatusText("long click by listener")
            true
        }
    }
    protected fun checkBoxChangeListener(){
        val func = {checkBox : CompoundButton, isChecked : Boolean ->
            var showText : String = checkBox.text.toString() + " "
            showText += if(isChecked) "checked" else "unchecked"
            _setStatusText(showText)
        }
        checkBoxJava.setOnCheckedChangeListener(func)
        checkBoxKotlin.setOnCheckedChangeListener(func)
    }
    protected fun radioChangeListener(){
        radioGroup1.setOnCheckedChangeListener(){checkBox : RadioGroup, radioId : Int ->
            var showText : String = findViewById<RadioButton>(radioId).text.toString()
            _setStatusText(showText)
        }
    }
    fun switchDogCat(view: View){
        imageDogCat.setImageResource(R.drawable.cat)
    }
    protected fun seekBarProgressListener(){
        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar : SeekBar, progress : Int , isFromUser : Boolean){
                _setStatusText("seek bar progress: ${progress},\n is from User ${isFromUser}.")
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                _setStatusText("seek bar start")
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                _setStatusText("seek bar stop")
            }
        })
    }
    protected fun buildListView1(){
        val adapterSimple : ArrayAdapter<String> = ArrayAdapter(this, R.layout.star_list_layout, R.id.starItemLabel, listOf<String>("html", "css", "js"))
        val adapterStar : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, listOf<String>("ajax", "sql", "cors"))
        val func = { adapterView : AdapterView<*>, view : View, position : Int, id : Long ->
            var showText : String = adapterView.getItemAtPosition(position).toString()
            _setStatusText(showText)
        }
        listView2.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        listView1.adapter = adapterSimple
        listView2.adapter = adapterStar
        listView1.setOnItemClickListener (func)
        listView2.setOnItemClickListener (func)
    }
    protected fun buildSpinner1(){
        val spinnerAdapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.num, android.R.layout.simple_spinner_item)
        spinner1.adapter = spinnerAdapter
        spinner1?.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                _setStatusText("spinner nothing selected")
            }

            override fun onItemSelected(dapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var showText : String = dapterView?.getItemAtPosition(position).toString()
                _setStatusText(showText)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuItemAbout -> {
                _setStatusText("About")
            }
            R.id.menuItemExit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        when(v){
            contextMenuInvoker -> {
                menu?.add(0, MENU_SMALL_FONT, 1, "Smaller font size" )
                menu?.add(0, MENU_BIG_FONT, 2, "Lager font size" )
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            MENU_SMALL_FONT -> contextMenuInvoker.textSize -= 1
            MENU_BIG_FONT -> contextMenuInvoker.textSize += 1
        }
        return super.onContextItemSelected(item)
    }
}

