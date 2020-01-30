package com.dublin.weather_kotlin.RV

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dublin.weather_kotlin.ADD_MY_WARNING
import com.dublin.weather_kotlin.MODEL.WARNING_MODEL
import com.dublin.weather_kotlin.R
import com.dublin.weather_kotlin.SQ_LITE
import java.util.*
//(MD. IMRUL MAHAMUD
//STUDENT ID:18317)
class RV_WARNNING(var c: Context) : RecyclerView.Adapter<RV_WARNNING.RVW>()
{
    var list: ArrayList<WARNING_MODEL>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVW {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.waen_list, parent, false)
        return RVW(v)
    }

    override fun onBindViewHolder(h: RVW, position: Int) {
        h.area.text = list[position].area
        h.level.text = list[position].level
        h.type.text = list[position].type

        // By click on item it move into the Add new warning page
        h.ll.setOnClickListener {
            c.startActivity(
                Intent(c, ADD_MY_WARNING::class.java)
                    .putExtra("id", list[position].id)
                    .putExtra("a", list[position].area)
                    .putExtra("t", list[position].type)
                    .putExtra("l", list[position].level)
                    .putExtra("del", "yes")
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class RVW(itemView: View) : ViewHolder(itemView) {
        var area: TextView
        var level: TextView
        var type: TextView
        var ll: LinearLayout

        init {
            area = itemView.findViewById(R.id.cv_country)
            level = itemView.findViewById(R.id.cv_level)
            type = itemView.findViewById(R.id.cv_type)
            ll = itemView.findViewById(R.id.ll_warn)
        }
    }

    init {
        list = ArrayList()
        val res = SQ_LITE(c).GET_WARNING()
        // Collection data from the sqlite to setup in the list as well step to step
        if (res.count == 0) {
        } else {
            while (res.moveToNext()) {
                val a = res.getString(res.getColumnIndex(SQ_LITE.Area))
                val l = res.getString(res.getColumnIndex(SQ_LITE.Level))
                val t = res.getString(res.getColumnIndex(SQ_LITE.Type))
                val i = res.getString(res.getColumnIndex(SQ_LITE.Id))
                list.add(WARNING_MODEL(a, l, t, i))
            }
        }
    }
}