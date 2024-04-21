package com.kotlin.api.skeleton.kotlin.api.skeleton.infrastructure.entity.weatherforecast

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherForecastEntity(
    val latitude: Float,
    val longitude: Float,
    val elevation: Float,
    @field: JsonProperty("generationtime_ms")
    val generationTimeMs: Float,
    @field: JsonProperty("utf_offset_seconds")
    val utcOffsetSeconds: Int,
    val timezone: String,
    @field: JsonProperty("timezone_abbreviation")
    val timezoneAbbreviation: String,
    val hourly: HourlyInfo,
    @field: JsonProperty("hourly_units")
    val hourlyUnits: HourlyUnits
)

data class HourlyInfo(
    val time: List<String>,
    @field: JsonProperty("temperature_2m")
    val temperature2m: List<Int>,
    @field: JsonProperty("weather_code")
    val weatherCode: Int
)

data class HourlyUnits(
    @field: JsonProperty("temperature_2m")
    val temperature2m: String
)
