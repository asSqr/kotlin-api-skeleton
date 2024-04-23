package com.kotlin.api.skeleton.kotlin.api.skeleton.application.model

import com.fasterxml.jackson.annotation.JsonProperty

class ForecastResponse(
    @JsonProperty("status")
    val apiStatus: String,
    val latitude: Float,
    val longitude: Float,
    val elevation: Float,
    @field: JsonProperty("generation_time_ms")
    val generationTimeMs: Float,
    @field: JsonProperty("utc_offset_seconds")
    val utcOffsetSeconds: Int,
    val timezone: String,
    @field: JsonProperty("timezone_abbreviation")
    val timezoneAbbreviation: String,
    val hourly: HourlyInfo,
    @field: JsonProperty("hourly_units")
    val hourlyUnits: HourlyUnits
)

const val SuccessfulStatus = "success"

data class HourlyInfo(
    val time: List<String>,
    @field: JsonProperty("temperature_2m")
    val temperature2m: List<Float>,
    @field: JsonProperty("weather_code")
    val weatherCode: List<Int>
)

data class HourlyUnits(
    val time: String,
    @field: JsonProperty("temperature_2m")
    val temperature2m: String,
    @field: JsonProperty("weather_code")
    val weatherCode: String
)
