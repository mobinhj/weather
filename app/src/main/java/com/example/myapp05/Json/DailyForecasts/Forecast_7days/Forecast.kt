package com.example.myapp05.Json.DailyForecasts.Forecast_7days


import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("daylight")
    val daylight: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("skyInfo")
    val skyInfo: String,
    @SerializedName("skyDescription")
    val skyDescription: String,
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
    @SerializedName("precipitationProbability")
    val precipitationProbability: String,
    @SerializedName("precipitationDesc")
    val precipitationDesc: String,
    @SerializedName("rainFall")
    val rainFall: String,
    @SerializedName("snowFall")
    val snowFall: String,
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
    @SerializedName("beaufortScale")
    val beaufortScale: String,
    @SerializedName("beaufortDescription")
    val beaufortDescription: String,
    @SerializedName("uvIndex")
    val uvIndex: String,
    @SerializedName("uvDesc")
    val uvDesc: String,
    @SerializedName("barometerPressure")
    val barometerPressure: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("iconName")
    val iconName: String,
    @SerializedName("iconLink")
    val iconLink: String,
    @SerializedName("dayOfWeek")
    val dayOfWeek: String,
    @SerializedName("weekday")
    val weekday: String,
    @SerializedName("utcTime")
    val utcTime: String
)