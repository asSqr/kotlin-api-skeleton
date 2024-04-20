package com.kotlin.api.skeleton.kotlin.api.skeleton.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> Logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}