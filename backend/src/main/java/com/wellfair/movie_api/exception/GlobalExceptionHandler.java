package com.wellfair.movie_api.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ApiError> handleConnectionError(
            ResourceAccessException exception) {
        return createError(
                HttpStatus.SERVICE_UNAVAILABLE,
                "No fue posible conectar con TMDB. Intenta más tarde.");
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ApiError> handleTmdbError(
            RestClientResponseException exception) {
        HttpStatus status = exception.getStatusCode().value() == 404
                ? HttpStatus.NOT_FOUND
                : HttpStatus.BAD_GATEWAY;

        String message = status == HttpStatus.NOT_FOUND
                ? "No se encontró la película solicitada."
                : "TMDB no pudo procesar la solicitud.";

        return createError(status, message);
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class
    })
    public ResponseEntity<ApiError> handleInvalidParameter(Exception exception) {
        return createError(
                HttpStatus.BAD_REQUEST,
                "Falta un parámetro o tiene un formato inválido.");
    }

    private ResponseEntity<ApiError> createError(
            HttpStatus status,
            String message) {
        ApiError error = new ApiError(
                Instant.now(),
                status.value(),
                message);

        return ResponseEntity.status(status).body(error);
    }
}