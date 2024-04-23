package com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast

sealed class ForecastResult {
    abstract val result: ForecastInfo

    data class Success(override val result: ForecastInfo) :
        ForecastResult()

    data class Failure(override val result: ForecastInfo) :
        ForecastResult()
}
