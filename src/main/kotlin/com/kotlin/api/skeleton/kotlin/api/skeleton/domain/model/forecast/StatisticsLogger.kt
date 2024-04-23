package com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast

import com.kotlin.api.skeleton.kotlin.api.skeleton.application.model.ForecastRequest
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.application.Logger
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

@Component
@RequestScope
class StatisticsLogger(private val logger: Logger) {

    fun handleRequestLog(requestBody: ForecastRequest) {
        logger.logLatitude(requestBody.latitude)
        logger.logLongitude(requestBody.longitude)
    }

    fun handleResultLog(result: ForecastResult) {
        if (result is ForecastResult.Success) {
            logger.logSuccessfulStatus()
        }

        result.result.apply {
            latitude.let { logger.logLatitude(it) }
            longitude.let { logger.logLongitude(it) }
        }
    }
}
