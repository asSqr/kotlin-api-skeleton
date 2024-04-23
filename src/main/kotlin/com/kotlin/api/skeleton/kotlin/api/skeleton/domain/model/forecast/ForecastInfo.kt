package com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast

data class ForecastInfo(
    val latitude: Float,
    val longitude: Float,
    val elevation: Float,
    val generationTimeMs: Float,
    val utcOffsetSeconds: Int,
    val timezone: String,
    val timezoneAbbreviation: String,
    val hourly: HourlyInfo,
    val hourlyUnits: HourlyUnits
)

data class HourlyInfo(
    val time: List<String>,
    val temperature2m: List<Float>,
    val weatherCode: List<Int>
)

data class HourlyUnits(
    val time: String,
    val temperature2m: String,
    val weatherCode: String
)
