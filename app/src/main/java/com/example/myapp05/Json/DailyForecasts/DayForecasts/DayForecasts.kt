package com.example.myapp05.Json.DailyForecasts.DayForecasts


import com.google.gson.annotations.SerializedName

data class DayForecasts(
    @SerializedName("observations")
    val observations: Observations,
    @SerializedName("feedCreation")
    val feedCreation: String,
    @SerializedName("metric")
    val metric: Boolean
)