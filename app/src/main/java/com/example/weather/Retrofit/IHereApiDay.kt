package com.example.weather.Retrofit

import android.text.Editable
import com.example.weather.Json.DailyForecasts.DayForecasts.DayForecasts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IHereApiDay {
    @GET("report.json")
    fun getWeatherDay(@Query("apiKey") apiKey: String,
                      @Query("app_id") app_id: String,
                      @Query("app_code") app_code: String,
                      @Query("name") name: Editable,
                      @Query("product") product: String,
                      @Query("oneObservation") oneObservation:Boolean)
            : Call<DayForecasts>
}