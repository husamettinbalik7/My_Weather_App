package com.balik.android.myweatherapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_response")
data class WeatherResponse(
    @PrimaryKey(autoGenerate = true)
    val id : Int =1,
    @SerializedName("current_weather")
    @Embedded
    val current_weather: CurrentWeather,
    @SerializedName("daily")
    @Embedded
    val daily: Daily
)