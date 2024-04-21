package com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model

import org.springframework.http.HttpStatus

class SkeletonException(
    val forecastError: SkeletonError,
    val description: String? = null,
    val throwable: Throwable? = null
): RuntimeException(description, throwable)

enum class SkeletonError(val apiStatus: String, val httpStatus: HttpStatus) {
    ValidationFormatError("1000", HttpStatus.BAD_REQUEST),
    ForecastFailureError("1001", HttpStatus.OK),
    WeatherForecastApiError("1002", HttpStatus.INTERNAL_SERVER_ERROR),
    UnexpectedError("9000", HttpStatus.INTERNAL_SERVER_ERROR)
}