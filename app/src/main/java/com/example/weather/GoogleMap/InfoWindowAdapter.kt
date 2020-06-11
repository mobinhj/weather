package com.example.weather.GoogleMap

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.weather.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowAdapter(context: Context):GoogleMap.InfoWindowAdapter {
    @SuppressLint("InflateParams")
    private val mWindow: View = (context as Activity).layoutInflater
        .inflate(R.layout.marker_info,null)
    private fun randomWindowText(marker: Marker, view: View){
        val title   = view.findViewById<TextView>(R.id.title)
        val snippet = view.findViewById<TextView>(R.id.snippet)
        title.text = marker.title
        snippet.text = marker.snippet
    }
    override fun getInfoContents(marker: Marker): View {
        randomWindowText(marker,mWindow)
        return mWindow
    }
    override fun getInfoWindow(marker: Marker): View {
        randomWindowText(marker,mWindow)
        return mWindow
    }
}