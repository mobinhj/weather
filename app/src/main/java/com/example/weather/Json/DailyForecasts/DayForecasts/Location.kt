package com.example.weather.Json.DailyForecasts.DayForecasts


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("observation")
    val observation: List<Observation>,
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