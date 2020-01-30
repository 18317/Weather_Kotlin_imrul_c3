package com.dublin.weather_kotlin
//(MD. IMRUL MAHAMUD
//STUDENT ID:18317)
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dublin.weather_kotlin.RV.RV_WARNNING

class My_Warning_list : AppCompatActivity() {

    //This code is show the warning add by me
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my__warning_list)
        val button = findViewById<Button>(R.id.add)
        button.setOnClickListener {
            val intent = Intent(this@My_Warning_list, ADD_MY_WARNING::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        GET_WARN()
    }

    fun GET_WARN() {
        val RV: RecyclerView = findViewById(R.id.RV_WARN)
        RV.setLayoutManager(LinearLayoutManager(this))
        val rv = RV_WARNNING(this)
        RV.setAdapter(rv)
    }
}
