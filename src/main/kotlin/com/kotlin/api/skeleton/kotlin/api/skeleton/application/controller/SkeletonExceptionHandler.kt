package com.kotlin.api.skeleton.kotlin.api.skeleton.application.controller

import com.kotlin.api.skeleton.kotlin.api.skeleton.application.model.ErrorResponse
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.SkeletonError
import com.kotlin.api.skeleton.kotlin.api.skeleton.domain.model.SkeletonException
import com.kotlin.api.skeleton.kotlin.api.skeleton.util.Logger
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class SkeletonExceptionHandler(
    private val applicationLogger: ApplicationLogger
): ResponseEntityExceptionHandler() {

    companion object {
        val log = Logger<SkeletonExceptionHandler>()
    }

    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        statusCode: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorResponse = (body as ErrorResponse?) ?: ErrorResponse(SkeletonError.UnexpectedError.apiStatus)

        applicationLogger.logStatus(errorResponse.apiStatus)

        if (statusCode.is5xxServerError) {
            log.error(statusCode.toString(), ex)
        } else {
            log.debug(statusCode.toString(), ex)
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request)!!
    }

    @ExceptionHandler(SkeletonException::class)
    fun handleApiException(ex: SkeletonException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(
            ex,
            ErrorResponse(ex.forecastError.apiStatus),
            HttpHeaders(),
            ex.forecastError.httpStatus,
            request
        )
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        return super.handleMethodArgumentNotValid(ex, headers, status, request)!!
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        return super.handleHttpMessageNotReadable(ex, headers, status, request)
    }

    @ExceptionHandler(Exception::class)
    fun unexpectedException(ex: Exception, request: WebRequest): ResponseEntity<Any> =
        when (ex.cause) {
            is SkeletonException -> {
                /**
                 * CompletableFutureの非同期処理中エラー等でラップされてしまう場合があるため、ここで処理する
                 */
                handleApiException(ex.cause as SkeletonException, request)
            }
            else -> {
                handleExceptionInternal(
                    ex,
                    ErrorResponse(SkeletonError.UnexpectedError.apiStatus),
                    HttpHeaders(),
                    SkeletonError.UnexpectedError.httpStatus,
                    request
                )
            }
        }

}