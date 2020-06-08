package com.example.weather.Json.DailyForecasts.Forecast_7days


import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("dailyForecasts")
    val dailyForecasts: DailyForecasts,
    @SerializedName("feedCreation")
    val feedCreation: String,
    @SerializedName("metric")
    val metric: Boolean
)