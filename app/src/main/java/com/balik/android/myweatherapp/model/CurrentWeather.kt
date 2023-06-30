package com.balik.android.myweatherapp.model


data class CurrentWeather(
    val is_day: Int,
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double
)