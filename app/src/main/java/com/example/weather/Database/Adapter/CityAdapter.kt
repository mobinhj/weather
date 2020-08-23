package com.example.weather.Database.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.Database.CityModel
import com.example.weather.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_database_city.view.*

class CityAdapter( private var exampleList : List<CityModel>):
    RecyclerView.Adapter<CityAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_database_city ,parent ,false )
        return ExampleViewHolder(itemView)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.city.text = currentItem.city
        holder.tempDate.text = currentItem.date.substring(0 , 10)
        holder.tempDay.text = currentItem.temp.toDouble().toInt().toString() + "°C"
        Picasso.get().load(currentItem.icon).into(holder.imageView)
    }
    override fun getItemCount() = exampleList.size
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val city : TextView = itemView.city
        val tempDay: TextView = itemView.tempDay
        val tempDate : TextView = itemView.date
        val imageView : ImageView = itemView.imageDay
    }
}