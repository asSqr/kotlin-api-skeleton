package com.kotlin.api.skeleton.kotlin.api.skeleton.infrastructure.repository

import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.SkeletonError
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.SkeletonException
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast.ForecastInfo
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast.HourlyInfo
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast.HourlyUnits
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.repository.WeatherForecastRepository
import com.kotlin.api.skeleton.kotlin.api.skeleton.infrastructure.Endpoint
import com.kotlin.api.skeleton.kotlin.api.skeleton.infrastructure.entity.weatherforecast.WeatherForecastEntity
import com.kotlin.api.skeleton.kotlin.api.skeleton.infrastructure.entity.weatherforecast.WeatherForecastParam
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

@Repository
class WeatherForecaseApiRepository(
    private val endpoint: Endpoint,
    private val restTemplate: RestTemplate
) : WeatherForecastRepository {

    @Async
    override fun forecast(latitude: Float, longitude: Float): CompletableFuture<ForecastInfo> {
        val weatherForecastInfo: WeatherForecastEntity = kotlin.runCatching {
            val body = restTemplate.exchange(
                RequestEntity<String>(
                    HttpHeaders(),
                    HttpMethod.GET,
                    endpoint.getUri(WeatherForecastParam(latitude, longitude))
                ),
                WeatherForecastEntity::class.java
            ).body!!
            body
        }.getOrElse {
            when (it) {
                is WeatherForecastApiRepositoryException -> {
                    throw SkeletonException(
                        SkeletonError.WeatherForecastApiError,
                        description = it.message
                    )
                }
                else -> {
                    throw SkeletonException(
                        SkeletonError.UnexpectedError,
                        description = it.message
                    )
                }
            }
        }

        return CompletableFuture.completedFuture(
            ForecastInfo(
                latitude = weatherForecastInfo.latitude,
                longitude = weatherForecastInfo.longitude,
                elevation = weatherForecastInfo.elevation,
                generationTimeMs = weatherForecastInfo.generationTimeMs,
                utcOffsetSeconds = weatherForecastInfo.utcOffsetSeconds,
                timezone = weatherForecastInfo.timezone,
                timezoneAbbreviation = weatherForecastInfo.timezoneAbbreviation,
                hourly = HourlyInfo(
                    time = weatherForecastInfo.hourly.time,
                    temperature2m = weatherForecastInfo.hourly.temperature2m,
                    weatherCode = weatherForecastInfo.hourly.weatherCode
                ),
                hourlyUnits = HourlyUnits(
                    time = weatherForecastInfo.hourlyUnits.time,
                    temperature2m = weatherForecastInfo.hourlyUnits.temperature2m,
                    weatherCode = weatherForecastInfo.hourlyUnits.weatherCode
                )
            )
        )
    }

    class WeatherForecastApiRepositoryException(message: String) : RuntimeException(message)
}
