package com.javacode.testtask.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Error insufficientFundsException(final InsufficientFundsException e) {
        log.debug("Получен статус 403 Forbidden {}", e.getMessage(), e);
        return Error.builder()
                .message(e.getMessage())
                .status(HttpStatus.FORBIDDEN.toString())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error insufficientFundsException(final NotFoundException e) {
        log.debug("Получен статус 404 Not found {}", e.getMessage(), e);
        return Error.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.toString())
                .build();
    }
}
