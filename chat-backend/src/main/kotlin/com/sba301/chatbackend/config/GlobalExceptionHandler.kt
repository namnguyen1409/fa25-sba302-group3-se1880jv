package com.sba301.chatbackend.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.time.LocalDateTime

/**
 * Global exception handler for the chat application
 * Provides consistent error responses across all endpoints
 */
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleValidationErrors(ex: WebExchangeBindException, exchange: ServerWebExchange): Mono<ResponseEntity<ErrorResponse>> {
        val errors = ex.bindingResult.fieldErrors.map { "${it.field}: ${it.defaultMessage}" }
        val error = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Validation Failed",
            message = errors.joinToString(", "),
            path = exchange.request.path.value()
        )
        return Mono.just(ResponseEntity.badRequest().body(error))
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException, exchange: ServerWebExchange): Mono<ResponseEntity<ErrorResponse>> {
        val error = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Bad Request",
            message = ex.message ?: "Something went wrong",
            path = exchange.request.path.value()
        )
        return Mono.just(ResponseEntity.badRequest().body(error))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: IllegalArgumentException, exchange: ServerWebExchange): Mono<ResponseEntity<ErrorResponse>> {
        val error = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Bad Request",
            message = ex.message ?: "Invalid request",
            path = exchange.request.path.value()
        )
        return Mono.just(ResponseEntity.badRequest().body(error))
    }

    @ExceptionHandler(IllegalAccessException::class)
    fun handleIllegalAccess(ex: IllegalAccessException, exchange: ServerWebExchange): Mono<ResponseEntity<ErrorResponse>> {
        val error = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.FORBIDDEN.value(),
            error = "Forbidden",
            message = ex.message ?: "Access denied",
            path = exchange.request.path.value()
        )
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(error))
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException, exchange: ServerWebExchange): Mono<ResponseEntity<ErrorResponse>> {
//        val error = ErrorResponse(
//            timestamp = LocalDateTime.now(),
//            status = HttpStatus.NOT_FOUND.value(),
//            error = "Not Found",
//            message = ex.message ?: "Resource not found",
//            path = exchange.request.path.value()
//        )
        return Mono.just(ResponseEntity.notFound().build())
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneral(ex: Exception, exchange: ServerWebExchange): Mono<ResponseEntity<ErrorResponse>> {
        val error = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = "Internal Server Error",
            message = "An unexpected error occurred",
            path = exchange.request.path.value()
        )
        println("Unhandled exception: ${ex.message}")
        return Mono.just(ResponseEntity.internalServerError().body(error))
    }
}

data class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val error: String,
    val message: String,
    val path: String
)
