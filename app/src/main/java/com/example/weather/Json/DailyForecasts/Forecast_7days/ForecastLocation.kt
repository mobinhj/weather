package com.example.weather.Json.DailyForecasts.Forecast_7days


import com.google.gson.annotations.SerializedName

data class ForecastLocation(
    @SerializedName("forecast")
    val forecast: List<Forecast>,
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("timezone")
    val timezone: Int
)