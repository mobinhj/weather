package com.example.weather.Json.DailyForecasts.DayForecasts


import com.google.gson.annotations.SerializedName

data class Observation(
    @SerializedName("daylight")
    val daylight: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("skyInfo")
    val skyInfo: String,
    @SerializedName("skyDescription")
    val skyDescription: String,
    @SerializedName("temperature")
    val temperature: String,
    @SerializedName("temperatureDesc")
    val temperatureDesc: String,
    @SerializedName("comfort")
    val comfort: String,
    @SerializedName("highTemperature")
    val highTemperature: String,
    @SerializedName("lowTemperature")
    val lowTemperature: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("dewPoint")
    val dewPoint: String,
    @SerializedName("precipitation1H")
    val precipitation1H: String,
    @SerializedName("precipitation3H")
    val precipitation3H: String,
    @SerializedName("precipitation6H")
    val precipitation6H: String,
    @SerializedName("precipitation12H")
    val precipitation12H: String,
    @SerializedName("precipitation24H")
    val precipitation24H: String,
    @SerializedName("precipitationDesc")
    val precipitationDesc: String,
    @SerializedName("airInfo")
    val airInfo: String,
    @SerializedName("airDescription")
    val airDescription: String,
    @SerializedName("windSpeed")
    val windSpeed: String,
    @SerializedName("windDirection")
    val windDirection: String,
    @SerializedName("windDesc")
    val windDesc: String,
    @SerializedName("windDescShort")
    val windDescShort: String,
    @SerializedName("barometerPressure")
    val barometerPressure: String,
    @SerializedName("barometerTrend")
    val barometerTrend: String,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("snowCover")
    val snowCover: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("iconName")
    val iconName: String,
    @SerializedName("iconLink")
    val iconLink: String,
    @SerializedName("ageMinutes")
    val ageMinutes: String,
    @SerializedName("activeAlerts")
    val activeAlerts: String,
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
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("utcTime")
    val utcTime: String
)