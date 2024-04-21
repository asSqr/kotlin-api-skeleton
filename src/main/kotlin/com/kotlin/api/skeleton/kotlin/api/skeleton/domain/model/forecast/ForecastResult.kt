package com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.forecast

sealed class ForecastResult {
    abstract val result: String

    data class Success(override val result: String) :
        ForecastResult()

    data class Failure(override val result: String) :
        ForecastResult()
}
