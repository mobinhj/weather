package com.example.myapp05.Retrofit

import com.example.myapp05.Json.DailyForecasts.DayForecasts.DayForecasts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IHereApiLocation {
    @GET("report.json")
    fun getWeatherLocation(
        @Query("app_id") app_id: String,
        @Query("app_code") app_code: String,
        @Query("product") product: String,
        @Query("oneobservation") oneobservation: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude:String
    )
            : Call<DayForecasts>
}