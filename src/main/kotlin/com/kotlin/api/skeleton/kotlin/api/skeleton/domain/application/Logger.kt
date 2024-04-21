package com.kotlin.api.skeleton.kotlin.api.skeleton.domain.application

interface Logger {

    fun logSuccessfulStatus()

    fun logLatitude(latitude: Float)

    fun logLongitude(longitude: Float)

}