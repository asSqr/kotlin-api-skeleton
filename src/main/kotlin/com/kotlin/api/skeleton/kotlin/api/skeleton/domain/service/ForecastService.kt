package com.kotlin.api.skeleton.kotlin.api.skeleton.domain.service

import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast.ForecastResult
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast.StatisticsLogger
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.repository.WeatherForecastRepository
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class ForecastService(
    private val logger: StatisticsLogger,
    private val weatherForecastRepository: WeatherForecastRepository
) {
    fun forecast(
        region: String
    ): ForecastResult {
        val weatherForecastTask = weatherForecastRepository.forecast(region)

        CompletableFuture.allOf(
            *arrayListOf<CompletableFuture<*>>()
                .apply {
                    add(weatherForecastTask)
                }
                .toTypedArray()
        ).join()

        return ForecastResult.Success(result = "success")
    }
}