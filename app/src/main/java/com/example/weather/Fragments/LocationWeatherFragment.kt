package com.example.weather.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weather.Json.DailyForecasts.DayForecasts.DayForecasts
import com.example.weather.R
import com.example.weather.Retrofit.IHereApiLocation
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_icone.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass.
 */
class LocationWeatherFragment : Fragment(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private val context: LocationWeatherFragment = this
    private var city :String? = ""
    private var country:String? = ""
    private var temp:String? = ""
    private var icon:String? = ""
    private var state:String? = ""
    private val baseUrl = "https://weather.api.here.com/weather/1.0/"
    private val appId   = "NGkPDa4koRes4s9mNW0s"
    private val appCode = "xdjT6t-y6emD9tugkEW_7w"
    private val product  = "observation"
    private val oneObservation = "true"
        companion object {
        var mapFragment : SupportMapFragment?=null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val rootView = inflater.inflate(R.layout.fragment_location_weather, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        return rootView
    }
    override fun onMapReady(googleMap : GoogleMap ){
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        val picasso = Picasso.get()
        mMap.setOnMapLongClickListener { latLng ->
            val markerOptions =  MarkerOptions()
            markerOptions.position(latLng)
            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val hereApi = retrofit.create(IHereApiLocation::class.java)
            val latitude = latLng.latitude.toString()
            val longitude = latLng.longitude.toString()
            val callLocationWeather  = hereApi
                .getWeatherLocation(appId,appCode,product,oneObservation,latitude,longitude)
            callLocationWeather.enqueue(object : Callback<DayForecasts> {
                override fun onFailure(call: Call<DayForecasts>, t: Throwable) {

                }
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<DayForecasts>, response:Response<DayForecasts>){
                    val location = response.body()?.observations?.location?.get(0)
                    city = location?.city
                    country = location?.country
                    state = location?.state
                    val observation = location?.observation?.get(0)
                     temp = observation?.temperature?.toDouble()?.toInt().toString()
                     icon = observation?.iconLink
                    val inflater = LayoutInflater.from(context.getContext())
                    val customView = inflater.inflate(R.layout.dialog_icone, null)
                    val dialogBuilder = AlertDialog.Builder(context.getContext(),R.style.CustomAlertDialog)
                    picasso.load(icon).into(customView.image_icon)
                    val hobbies = arrayOf("$city,$state,$country\n temperature: $temp°C")
                    Log.i("console", "$state,$city,$country,$temp°C")
                    val textView = TextView(context.getContext())
                    textView.text = "Location Weather Info :"
                    textView.setPadding(40, 30, 20, 30)
                    textView.textSize = 20f
                    textView.setBackgroundColor(Color.TRANSPARENT)
                    textView.setTextColor(Color.BLACK)
                    val items = dialogBuilder.setView(customView).setCustomTitle(textView)
                        .setItems(hobbies) { dialog, which ->
                        }
                    items.show()
                }
            })
            mMap.clear()
            mMap.addMarker(markerOptions)
        }
    }
}

