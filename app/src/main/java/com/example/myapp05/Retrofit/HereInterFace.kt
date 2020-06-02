package com.example.myapp05.Retrofit

import android.text.Editable
import com.example.myapp05.Json.DailyForecasts.Forecast_7days.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IHereApi {

@GET("report.json")
    fun getWeather(@Query("apiKey") apiKey: String,
                   @Query("name") name: Editable,
                   @Query("product") product: String,
                   @Query("app_id") app_id:String,
                   @Query("app_code") app_code :String)
        : Call<WeatherModel>


}