package com.example.myapp05.Fragments

//import com.example.myapp05.GoogleMap.CustomInfoWindowGoogleMap

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapp05.Json.DailyForecasts.DayForecasts.DayForecasts
import com.example.myapp05.R
import com.example.myapp05.Retrofit.IHereApiLocation
import com.google.android.gms.maps.CameraUpdateFactory
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
    val context: LocationWeatherFragment = this
    var city :String? = ""
    var country:String? = ""
    var temp:String? = ""
    var icone:String? = ""
    var state:String? = ""
    private val BASE_URL = "https://weather.api.here.com/weather/1.0/"
    val app_id   = "NGkPDa4koRes4s9mNW0s"
    val app_code = "xdjT6t-y6emD9tugkEW_7w"
    val product  = "observation"
    val oneobservation = "true"
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
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val hereApi = retrofit.create(IHereApiLocation::class.java)
            val latitude = latLng.latitude.toString()
            val longitude = latLng.longitude.toString()
            val callLocationWeather  = hereApi
                .getWeatherLocation(app_id,app_code,product,oneobservation,latitude,longitude)
            callLocationWeather.enqueue(object : Callback<DayForecasts> {
                override fun onFailure(call: Call<DayForecasts>, t: Throwable) {

                }
                override fun onResponse(call: Call<DayForecasts>,response:Response<DayForecasts>){
                    val location = response.body()?.observations?.location?.get(0)
                    city = location?.city
                    country = location?.country
                    state = location?.state
                    val observation = location?.observation?.get(0)
                     temp = observation?.temperature?.toDouble()?.toInt().toString()
                     icone = observation?.iconLink
                    val inflater = LayoutInflater.from(context.getContext())
                    val customView = inflater.inflate(R.layout.dialog_icone, null)
                    val dialogBuilder = AlertDialog.Builder(context.getContext())
                    picasso.load(icone).into(customView.image_icone)
                    val hobbies = arrayOf(city+","+state+","+country+ "\n" +" temperature: "+temp+"°C")
                    Log.i("console",state+","+city +","+country+","+temp+"°C")
                    val textView = TextView(context.getContext())
                    textView.text = "Location Weather Info"
                    textView.setPadding(40, 30, 20, 30)
                    textView.textSize = 20f
                    textView.setBackgroundColor(Color.CYAN)
                    textView.setTextColor(Color.BLACK)
                    val items = dialogBuilder.setView(customView).setCustomTitle(textView)
                        .setItems(hobbies, DialogInterface.OnClickListener { dialog, which ->
                        })
                    items.show()
                }
            })
            mMap.clear()
            mMap.addMarker(markerOptions)
        }
    }
}

