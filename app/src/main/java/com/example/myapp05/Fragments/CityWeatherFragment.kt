package com.example.myapp05.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.LruCache
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp05.Json.DailyForecasts.DayForecasts.DayForecasts
import com.example.myapp05.Json.DailyForecasts.Forecast_7days.Forecast
import com.example.myapp05.Json.DailyForecasts.Forecast_7days.WeatherModel
import com.example.myapp05.R
import com.example.myapp05.Retrofit.IHereApi
import com.example.myapp05.Retrofit.IHereApiDay
import com.example.mylistview.MyListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_city_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class CityWeatherFragment : Fragment() {
    private val BASE_URL = "https://weather.api.here.com/weather/1.0/"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val product1 = "forecast_7days_simple"
        val app_id = "NGkPDa4koRes4s9mNW0s"
        val app_code = "xdjT6t-y6emD9tugkEW_7w"
        val product = "observation"
        val name = cityEditText.text
        val apiKey = "1r2mYo9MtRCoYu68HWa0R0Kru4axR2YwZSQATuPxx1U"
        val oneobservation = true

        fun AppCompatActivity.hideKeyboard() {
            val view = this.currentFocus
            if (view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
        fun Fragment.hideKeyboard() {
            val activity = this.activity
            if (activity is AppCompatActivity) {
                activity.hideKeyboard()
            }
        }
        cityEditText.setOnEditorActionListener { v, actionId, event ->
            update_at.text = Date().toString()
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                if (name.isNotEmpty()) {
                    val builder = AlertDialog.Builder(context)
                    builder.setCancelable(false) // if you want user to wait for some process to finish,
                    builder.setView(R.layout.progress)
                    val dialog: AlertDialog = builder.create()
                    dialog.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))
                    dialog.show()

                    val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val retrofitDay = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val hereApi = retrofit.create(IHereApi::class.java)
                    val hereApiDay = retrofitDay.create(IHereApiDay::class.java)
                    val callDay =
                        hereApiDay.getWeatherDay(
                            apiKey,
                            app_id,
                            app_code,
                            name,
                            product,
                            oneobservation
                        )
                    callDay.enqueue(object : Callback<DayForecasts> {
                        override fun onFailure(call: Call<DayForecasts>, t: Throwable) {
                            Log.e("console", t.message, t)
                        }
                        @SuppressLint("SetTextI18n")
                        override fun onResponse(
                            call: Call<DayForecasts>,
                            response: Response<DayForecasts>
                        ) {
                            val location= response.body()?.observations?.location?.get(0)
                            val city   = location?.city
                            val country= location?.country
                            val observation = location?.observation?.get(0)
                            val temp   = observation?.temperature?.toDouble()?.toInt().toString()
                            val lat  = observation?.latitude
                            val long = observation?.longitude
                            val icon = observation?.iconLink
                            val picasso= Picasso.get()
                            if (city != null) {
                                latTextView.text  = "Lat: " + lat.toString()
                                longTextView.text = "Long: "+ long.toString()
                                tempView.text     = "$temp°C"
                                address.text      = "$city,$country"
                                picasso.load(icon).into(imageSeason)
                                dialog.dismiss()
                            } else {
                                dialog.dismiss()
                                Toast.makeText(context, "City Not Found ", Toast.LENGTH_LONG).show()
                                tempView.text = "0°C"
                                address.text = null
                               imageSeason.setImageResource(0)
                                latTextView.text  = null
                                longTextView.text = null
                            }
                        }
                    })
                    val call = hereApi.getWeather(apiKey, name, product1, app_id, app_code)
                    call.enqueue(object : Callback<WeatherModel?> {
                        override fun onFailure(call: Call<WeatherModel?>, t: Throwable) {
                        }
                        @SuppressLint("WrongConstant")
                        override fun onResponse(
                            call: Call<WeatherModel?>,
                            response: Response<WeatherModel?>
                        ) {
                            val forecasts = response.body()?.dailyForecasts?.forecastLocation?.forecast
                            val list = mutableListOf<Forecast>()
                            if (forecasts != null) {
                                for (i in 0 until forecasts.count()) {
                                    forecasts[i].let { it1 -> list.add(it1) }
                                }
                            }
                            recycler_view.apply {
                                layoutManager =
                                    LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
                                adapter = MyListAdapter(list)
                            }
                        }
                    })
                    cityEditText.text.clear()
                } else Toast.makeText(this.activity, "Enter the city", Toast.LENGTH_LONG).show()
               true
            }
            false
        }
        }
    }


