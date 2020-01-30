package com.dublin.weather_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import java.util.*
//(MD. IMRUL MAHAMUD
//STUDENT ID:18317)
class ADD_MY_WARNING : AppCompatActivity() {
    // all veriable which are going to use in that calss
    var s_area = ""
    var s_level = ""
    var s_type = ""
    var btn_save: Button? = null
    var btn_del: Button? = null
    var btn_close: Button? = null
    var btn_Add: Button? = null
    var del: String? = null
    var id: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__my__warning)
        // Data base object to use databse value funtions
        val db = SQ_LITE(this)
        val spin_area = findViewById<Spinner>(R.id.spinner_area)
        val spin_leavel = findViewById<Spinner>(R.id.spinner_level)
        val spin_type = findViewById<Spinner>(R.id.spinner_type)
        type_list =
            Arrays.asList(*resources.getStringArray(R.array.type))
        level_list =
            Arrays.asList(*resources.getStringArray(R.array.level))

        // initionlization of all verable which belong to front end
        btn_Add = findViewById(R.id.btn_add_warn)
        btn_del = findViewById(R.id.btn_delete_warn)
        btn_save = findViewById(R.id.btn_save_warn)
        btn_close = findViewById(R.id.btn_close_warn)
        if (intent.getStringExtra("del") != null) {
            btn_Add!!.setVisibility(View.GONE)
            btn_del!!.setVisibility(View.VISIBLE)
            btn_save!!.setVisibility(View.VISIBLE)
            id = intent.getStringExtra("id")
            s_area = intent.getStringExtra("a")
            s_level = intent.getStringExtra("l")
            s_type = intent.getStringExtra("t")

            val int_type = type_list!!.indexOf(s_type)
            val int_level = type_list!!.indexOf(s_level)

            spin_leavel.setSelection(int_level)
            spin_type.setSelection(int_type)
        } else {
            btn_Add!!.setVisibility(View.VISIBLE)
            btn_del!!.setVisibility(View.GONE)
            btn_save!!.setVisibility(View.GONE)
        }
        // Drop down list setup  for araea
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.area,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_area.adapter = adapter
        // Drop down list setup  for level

        val adapterl = ArrayAdapter.createFromResource(
            this,
            R.array.level,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_leavel.adapter = adapterl
        // Drop down list setup  for type
        val adaptert = ArrayAdapter.createFromResource(
            this,
            R.array.type,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_type.adapter = adaptert
        spin_area.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                s_area = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        // item slection for level
        spin_leavel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                s_level = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        // item slection for type

        spin_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                s_type = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        // Button to click the funtion of go back from the current page to close this page
        findViewById<View>(R.id.btn_add_warn).setOnClickListener {
            val db = SQ_LITE(applicationContext)
            val res = db.INSERT_NEW_USER_DATA(s_area, s_level, s_type)
            if (res) {
                finish()
            }
        }
        // this button is used to delete the warning
        btn_del!!.setOnClickListener{
            val res = db.delete_ALL(id!!)
            if (res > 0) {
                finish()
            }
        }
        // this button is use to save the warning
        btn_save!!.setOnClickListener {
            val db = SQ_LITE(applicationContext)
            val res = db.update_all(id!!, s_area, s_level, s_type)
            if (res > 0) {
                finish()
            }
        }
        btn_close!!.setOnClickListener { finish() }
    }

    companion object {
        var type_list: List<String>? = null
        var level_list: List<String>? = null
    }
}
