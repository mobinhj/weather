package com.example.weather.Fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weather.Database.CitiesDBHelper
import com.example.weather.Database.CityModel
import com.example.weather.Json.DailyForecasts.DayForecasts.DayForecasts
import com.example.weather.R
import com.example.weather.Retrofit.IHereApiLocation
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_icone.view.*
import kotlinx.android.synthetic.main.fragment_location_weather.*
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
    private lateinit var citiesDBHelper: CitiesDBHelper
    private val context: LocationWeatherFragment = this

        companion object {
        var mapFragment : SupportMapFragment?=null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        citiesDBHelper = this.activity?.let { CitiesDBHelper(it) }!!
        val rootView = inflater.inflate(R.layout.fragment_location_weather, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        return rootView
    }
    override fun onMapReady(googleMap : GoogleMap ){
        var city :String?
        var country:String?
        var temp: String?
        var icon:String?
        var state:String?
        val baseUrl = "https://weather.api.here.com/weather/1.0/"
        val appId   = "NGkPDa4koRes4s9mNW0s"
        val appCode = "xdjT6t-y6emD9tugkEW_7w"
        val product  = "observation"
        val oneObservation = "true"
        mMap = googleMap
        when (mMap.mapType) {
            GoogleMap.MAP_TYPE_SATELLITE -> {
                map_type_satellite_background.visibility = View.VISIBLE
                map_type_satellite_text.setTextColor(Color.BLUE)
            }
            GoogleMap.MAP_TYPE_TERRAIN -> {
                map_type_terrain_background.visibility = View.VISIBLE
                map_type_terrain_text.setTextColor(Color.BLUE)
            }
            else -> {
                map_type_default_background.visibility = View.VISIBLE
                map_type_default_text.setTextColor(Color.BLUE)
            }
        }
        map_type_FAB.setOnClickListener {
            val anim = ViewAnimationUtils.createCircularReveal(
                map_type_selection,
                map_type_selection.width - (map_type_FAB.width / 2),
                map_type_FAB.height / 2,
                map_type_FAB.width / 2f,
                map_type_selection.width.toFloat())
            anim.duration = 200
            anim.interpolator = AccelerateDecelerateInterpolator()
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    map_type_selection.visibility = View.VISIBLE
                }
            })
            anim.start()
            map_type_FAB.visibility = View.INVISIBLE
        }
        map_type_default.setOnClickListener {
            map_type_default_background.visibility = View.VISIBLE
            map_type_satellite_background.visibility = View.INVISIBLE
            map_type_terrain_background.visibility = View.INVISIBLE
            map_type_default_text.setTextColor(Color.BLUE)
            map_type_satellite_text.setTextColor(Color.parseColor("#808080"))
            map_type_terrain_text.setTextColor(Color.parseColor("#808080"))
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
        map_type_satellite.setOnClickListener {
            map_type_default_background.visibility = View.INVISIBLE
            map_type_satellite_background.visibility = View.VISIBLE
            map_type_terrain_background.visibility = View.INVISIBLE
            map_type_default_text.setTextColor(Color.parseColor("#808080"))
            map_type_satellite_text.setTextColor(Color.BLUE)
            map_type_terrain_text.setTextColor(Color.parseColor("#808080"))
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
        map_type_terrain.setOnClickListener {
            map_type_default_background.visibility = View.INVISIBLE
            map_type_satellite_background.visibility = View.INVISIBLE
            map_type_terrain_background.visibility = View.VISIBLE
            map_type_default_text.setTextColor(Color.parseColor("#808080"))
            map_type_satellite_text.setTextColor(Color.parseColor("#808080"))
            map_type_terrain_text.setTextColor(Color.BLUE)
            mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        }
        mMap.uiSettings.isZoomControlsEnabled = true
        val picasso = Picasso.get()
        mMap.setOnMapClickListener{
            map_type_selection.visibility = View.INVISIBLE
            map_type_FAB.visibility = View.VISIBLE
        }
        mMap.setOnMapLongClickListener { latLng ->
            map_type_selection.visibility = View.INVISIBLE
            if (map_type_FAB.visibility == View.INVISIBLE) {
                val anim = ViewAnimationUtils.createCircularReveal(
                    map_type_selection,
                    map_type_selection.width - (map_type_FAB.width / 2),
                    map_type_FAB.height / 2,
                    map_type_selection.width.toFloat(),
                    map_type_FAB.width / 2f)
                anim.duration = 200
                anim.interpolator = AccelerateDecelerateInterpolator()
                anim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        map_type_selection.visibility = View.INVISIBLE
                    }
                })
                Handler().postDelayed({
                    kotlin.run {
                        map_type_FAB.visibility = View.VISIBLE
                    }
                }, 100)
                anim.start()
            }
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
                    val textView = TextView(context.getContext())
                    val date = observation?.utcTime?.substring(0 ,10).toString()
                    textView.text = "Location Weather Info :"
                    textView.setPadding(40, 30, 20, 30)
                    textView.textSize = 20f
                    textView.setBackgroundColor(Color.TRANSPARENT)
                    textView.setTextColor(Color.BLACK)
                    val cit = city.toString()
                    citiesDBHelper.insertCity(CityModel(cit,date,temp.toString(),icon.toString()))
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

