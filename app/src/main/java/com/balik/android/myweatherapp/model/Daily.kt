package com.balik.android.myweatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_table")
data class Daily(
    @PrimaryKey(autoGenerate = true)
    val id :Int = 1,
    val apparent_temperature_max: List<Double>,
    val apparent_temperature_min: List<Double>,
    val time: List<String>

)