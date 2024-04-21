package com.kotlin.api.skeleton.kotlin.api.skeleton.domain.repository

import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast.ForecastInfo
import java.util.concurrent.CompletableFuture

interface WeatherForecastRepository {

    fun forecast(latitude: Float, longitude: Float): CompletableFuture<ForecastInfo>
}
