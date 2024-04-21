package com.kotlin.api.skeleton.kotlin.api.skeleton.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Duration
import java.util.function.Supplier

@Configuration
class RestTemplateConfig(
    @Value("\${repository.connection-timeout}")
    private val connectionTimeout: Int,
    @Value("\${repository.read-timeout}")
    private val readTimeout: Int
) {
    val bufferingFactorySupplier = Supplier<ClientHttpRequestFactory> {
        BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory())
    }

    @Bean
    fun restTemplate(
        builder: RestTemplateBuilder
    ): RestTemplate = builder
        .setConnectTimeout(Duration.ofSeconds(connectionTimeout.toLong()))
        .setReadTimeout(Duration.ofSeconds(readTimeout.toLong()))
        .requestFactory(bufferingFactorySupplier)
        .build()
}
