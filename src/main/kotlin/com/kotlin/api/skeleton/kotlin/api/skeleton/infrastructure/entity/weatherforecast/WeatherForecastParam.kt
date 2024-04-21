package com.kotlin.api.skeleton.kotlin.api.skeleton.infrastructure.entity.weatherforecast

data class WeatherForecastParam(
    val latitude: Float,
    val longitude: Float,
    val hourly: String = "temperature_2m,weather_code"
)
