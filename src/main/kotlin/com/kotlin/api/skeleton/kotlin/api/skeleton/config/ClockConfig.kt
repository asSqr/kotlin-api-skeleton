package com.kotlin.api.skeleton.kotlin.api.skeleton.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.ZoneId

@Configuration
class ClockConfig {

    @Bean
    fun clock(): Clock {
        return Clock.system(ZoneId.of("Asia/Tokyo"))
    }

}