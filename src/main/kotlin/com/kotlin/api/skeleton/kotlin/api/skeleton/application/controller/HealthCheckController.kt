package com.kotlin.api.skeleton.kotlin.api.skeleton.application.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * ヘルスチェックコントローラーです．
 */
@RestController
class HealthCheckController {

    /**
     * /healthエンドポイントです．
     */
    @GetMapping("health")
    fun checkHealth(): String {
        return "Alive"
    }

}