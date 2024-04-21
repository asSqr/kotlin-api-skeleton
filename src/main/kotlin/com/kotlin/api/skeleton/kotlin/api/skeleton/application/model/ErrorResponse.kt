package com.kotlin.api.skeleton.kotlin.api.skeleton.application.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
    @JsonProperty("status")
    val apiStatus: String
)
