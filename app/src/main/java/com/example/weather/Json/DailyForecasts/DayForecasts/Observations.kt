package com.example.weather.Json.DailyForecasts.DayForecasts


import com.google.gson.annotations.SerializedName

data class Observations(
    @SerializedName("location")
    val location: List<Location>
)