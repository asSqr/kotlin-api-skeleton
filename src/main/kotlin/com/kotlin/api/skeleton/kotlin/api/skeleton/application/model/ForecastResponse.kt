package com.kotlin.api.skeleton.kotlin.api.skeleton.application.model

import com.fasterxml.jackson.annotation.JsonProperty

class ForecastResponse(
    @JsonProperty("status")
    val apiStatus: String
)

const val SuccessfulStatus = "success"
