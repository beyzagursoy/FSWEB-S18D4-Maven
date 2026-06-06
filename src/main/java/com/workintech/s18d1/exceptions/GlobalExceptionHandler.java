package com.workintech.s18d1.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BurgerException.class)
    public ResponseEntity<BurgerErrorResponse> handleException(BurgerException exception) {
        log.error("BurgerException occurred! Message: {}, Status: {}", exception.getMessage(), exception.getHttpStatus());
        BurgerErrorResponse response = new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BurgerErrorResponse> handleException(Exception exception) {
        log.error("An unexpected error occurred! Message: {}", exception.getMessage());
        BurgerErrorResponse response = new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
