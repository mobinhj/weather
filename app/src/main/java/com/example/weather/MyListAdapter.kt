package com.example.mylistview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.weather.Json.DailyForecasts.Forecast_7days.Forecast
import com.example.weather.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_adapter.view.*


class MyListAdapter ( private var exampleList : List<Forecast>): Adapter<MyListAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_adapter ,parent ,false )

        return ExampleViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.day.text = currentItem.weekday.substring(0 , 3)
        holder.tMax.text = currentItem.highTemperature.toDouble().toInt().toString() + "°"
        holder.tMin.text = currentItem.lowTemperature.toDouble().toInt().toString() + "°"
        Picasso.get().load(currentItem.iconLink).into(holder.imageView)
    }
    override fun getItemCount() = exampleList.size
    class ExampleViewHolder(itemView:View ) : RecyclerView.ViewHolder(itemView){
        val day :TextView = itemView.day
        val tMax:TextView = itemView.tMaxD
        val tMin :TextView = itemView.tMinD
        val imageView :ImageView = itemView.imageD
    }
}