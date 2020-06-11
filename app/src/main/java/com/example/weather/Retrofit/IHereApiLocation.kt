package com.example.weather.Retrofit

import com.example.weather.Json.DailyForecasts.DayForecasts.DayForecasts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IHereApiLocation {
    @GET("report.json")
    fun getWeatherLocation(
        @Query("app_id") app_id: String,
        @Query("app_code") app_code: String,
        @Query("product") product: String,
        @Query("oneObservation") oneObservation: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude:String)
            : Call<DayForecasts>
}