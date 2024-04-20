package com.kotlin.api.skeleton.kotlin.api.skeleton.domain.repository

import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast.WeatherForecastInfo
import java.util.concurrent.CompletableFuture

interface WeatherForecastRepository {

    fun forecast(region: String): CompletableFuture<WeatherForecastInfo>

}