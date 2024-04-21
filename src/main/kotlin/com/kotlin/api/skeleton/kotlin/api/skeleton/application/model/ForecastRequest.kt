package com.kotlin.api.skeleton.kotlin.api.skeleton.application.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ForecastRequest(
    val latitude: Float,
    val longitude: Float
)