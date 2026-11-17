package com.example.FlashSaleInventory.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatus(
            ResponseStatusException exception
    ) {
        String message = exception.getReason() != null
                ? exception.getReason()
                : "Request failed";

        return error(exception.getStatusCode().value(), message, List.of());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            NotFoundException exception
    ) {
        return error(404, exception.getMessage(), List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRequestBody(
            MethodArgumentNotValidException exception
    ) {
        List<String> errors = new ArrayList<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        return error(400, "Request validation failed", errors);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidParameters(
            HandlerMethodValidationException exception
    ) {
        List<String> errors = new ArrayList<>();

        for (ParameterValidationResult result :
                exception.getParameterValidationResults()) {
            String parameterName = result.getMethodParameter().getParameterName();

            if (parameterName == null) {
                parameterName = "parameter"
                        + result.getMethodParameter().getParameterIndex();
            }

            for (MessageSourceResolvable validationError :
                    result.getResolvableErrors()) {
                errors.add(
                        parameterName + ": " + validationError.getDefaultMessage()
                );
            }
        }

        return error(
                exception.getStatusCode().value(),
                "Request validation failed",
                errors
        );
    }

    private ResponseEntity<Map<String, Object>> error(
            int status,
            String message,
            List<String> errors
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status);
        body.put("message", message);
        body.put("errors", errors);

        return ResponseEntity.status(status).body(body);
    }
}