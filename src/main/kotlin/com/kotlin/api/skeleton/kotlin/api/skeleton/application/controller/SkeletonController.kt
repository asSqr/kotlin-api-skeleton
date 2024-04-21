package com.kotlin.api.skeleton.kotlin.api.skeleton.application.controller

import com.kotlin.api.skeleton.kotlin.api.skeleton.application.model.ForecastRequest
import com.kotlin.api.skeleton.kotlin.api.skeleton.application.model.ForecastResponse
import com.kotlin.api.skeleton.kotlin.api.skeleton.application.model.SuccessfulStatus
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.SkeletonError
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.SkeletonException
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast.ForecastResult
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast.StatisticsLogger
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.service.ForecastService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SkeletonController(
    private val forecastService: ForecastService,
    private val logger: StatisticsLogger
) {
    @PostMapping(value = ["/forecast"])
    fun forecast(
        @Validated
        @RequestBody(required = true)
        requestBody: ForecastRequest
    ): ForecastResponse {
        logger.handleRequestLog(requestBody)

        val result = forecastService
            .forecast(requestBody.latitude, requestBody.longitude)

        logger.handleResultLog(result)

        return when(result) {
            is ForecastResult.Success -> {
                ForecastResponse(apiStatus = SuccessfulStatus)
            }
            is ForecastResult.Failure -> {
                throw SkeletonException(SkeletonError.ForecastFailureError)
            }
        }
    }
}