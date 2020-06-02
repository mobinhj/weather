package com.example.myapp05.Retrofit

import android.text.Editable
import com.example.myapp05.Json.DailyForecasts.DayForecasts.DayForecasts
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
                      @Query("oneobservation") oneobservation:Boolean
    )
            : Call<DayForecasts>
}