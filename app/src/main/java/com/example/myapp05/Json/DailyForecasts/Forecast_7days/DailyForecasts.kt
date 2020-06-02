package com.example.myapp05.Json.DailyForecasts.Forecast_7days


import com.google.gson.annotations.SerializedName

data class DailyForecasts(
    @SerializedName("forecastLocation")
    val forecastLocation: ForecastLocation
)