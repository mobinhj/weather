package com.example.weather.Fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.Database.Adapter.CityAdapter
import com.example.weather.Database.CitiesDBHelper
import com.example.weather.Database.CityModel
import com.example.weather.R
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment :Fragment() {
    private lateinit var citiesDBHelper: CitiesDBHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        citiesDBHelper = this.activity?.let { CitiesDBHelper(it) }!!
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
    @SuppressLint("SetTextI18n", "WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val cities = citiesDBHelper.readAllCities()
        val list = mutableListOf<CityModel>()
        for (i in 0 until cities.count()) {
            cities[i].let { it1 -> list.add(it1) }
        }
        recycler_view_database.apply {
            layoutManager =
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL ,false)
            layoutManager
            adapter = CityAdapter(list)
        }
        database.text = "Fetched ${cities.size} city"
        database.setTextColor(Color.parseColor("#0aad3f"))
    }
}