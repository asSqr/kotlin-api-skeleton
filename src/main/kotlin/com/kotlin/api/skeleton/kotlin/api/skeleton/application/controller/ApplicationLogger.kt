package com.kotlin.api.skeleton.kotlin.api.skeleton.application.controller

import com.kotlin.api.skeleton.kotlin.api.skeleton.application.model.ApplicationLogEnum
import com.kotlin.api.skeleton.kotlin.api.skeleton.application.model.SuccessfulStatus
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.application.Logger
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

@Component
@RequestScope
class ApplicationLogger: Logger {

    companion object {
        fun init() {
            MDC.clear()
        }
    }

    fun logStatus(status: String) {
        MDC.put(ApplicationLogEnum.RESPONSE_STATUS.columnName, status)
    }

    override fun logSuccessfulStatus() {
        logStatus(SuccessfulStatus)
    }

    override fun logRegion(region: String) {
        MDC.put(ApplicationLogEnum.REGION.columnName, region)
    }

}