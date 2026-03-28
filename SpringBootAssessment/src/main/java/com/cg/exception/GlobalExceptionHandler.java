package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAll(Exception ex) {
        return new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
    }
}