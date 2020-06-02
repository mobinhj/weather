package com.example.myapp05.Json.DailyForecasts.DayForecasts


import com.google.gson.annotations.SerializedName

data class Observations(
    @SerializedName("location")
    val location: List<Location>
)