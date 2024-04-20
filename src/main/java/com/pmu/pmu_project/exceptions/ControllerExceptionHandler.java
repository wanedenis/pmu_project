package com.pmu.pmu_project.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final int BAD_REQUEST_STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    private static final int INTERNAL_SERVER_ERROR_STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> illegalArgumentExceptionHandler(IllegalArgumentException ex, WebRequest request) {
        String errorMessage = "An error occurred while processing the request.";
        log.error("Exception occurred: {}", ex.getMessage());
        ErrorMessage message = new ErrorMessage(
                BAD_REQUEST_STATUS_CODE,
                new Date(),
                errorMessage,
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        String errorMessage = "An internal server error occurred.";
        log.error("Exception occurred: {}", ex.getMessage());
        ErrorMessage message = new ErrorMessage(
                INTERNAL_SERVER_ERROR_STATUS_CODE,
                new Date(),
                errorMessage,
                request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}