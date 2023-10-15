package com.example.recyclerview

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ColorData (
    var colorName: String,
    var colorHex: Int)

class Adapter (private val context: Context,
               private val list: ArrayList<ColorData>,
                private val cellClickListener: CellClickListener) :
               RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        var colorView: View? = null
        var textView: TextView? = null
        init {
            colorView = itemView.findViewById(R.id.view)
            textView = itemView.findViewById(R.id.textView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(list[position].colorName, context)
        }

        holder.colorView?.setBackgroundColor(list[position].colorHex)
        holder.textView?.text = list[position].colorName
    }

    override fun getItemCount(): Int {
        return list.size 
    }
}

interface CellClickListener {
    fun onCellClickListener(text: String, context: Context) {
        Toast.makeText(context,"Это " + text, LENGTH_SHORT).show();
    }
}

class MainActivity : AppCompatActivity(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.rView);
        recyclerView.layoutManager = LinearLayoutManager(this)
        val ColorList = ArrayList<ColorData>()
        ColorList.add(ColorData("Серый",0xFF474747.toInt()))
        ColorList.add(ColorData("Красный",0xFFFF0000.toInt()))
        ColorList.add(ColorData("Синий", 0xFF002AFF.toInt()))
        ColorList.add(ColorData("Зеленый", 0xFF00FF22.toInt()))
        ColorList.add(ColorData("Желтый", 0xFFEEFF00.toInt()))
        recyclerView.adapter = Adapter(this, ColorList, this)
    }
}