package com.kotlin.api.skeleton.kotlin.api.skeleton.infrastructure

import com.kotlin.api.skeleton.kotlin.api.skeleton.infrastructure.entity.weatherforecast.WeatherForecastParam
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Component
class Endpoint(
    @Value("\${weather-forecast.api-url}")
    private var weatherForecastUrl: String
) {
    fun getUri(param: WeatherForecastParam): URI =
        URI(
            UriComponentsBuilder
                .fromHttpUrl("$weatherForecastUrl/forecast")
                .apply {
                    queryParam("longitude", param.longitude)
                    queryParam("latitude", param.latitude)
                    queryParam("hourly", param.hourly)
                }
                .toUriString()
        )
}
