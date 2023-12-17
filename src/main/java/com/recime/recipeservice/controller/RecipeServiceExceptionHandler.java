package com.recime.recipeservice.controller;

import com.recime.recipeservice.data.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RecipeServiceExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("ValidationError");
        errorResponse.setMessage("Invalid request parameters");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("ValidationError");
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("UnsupportedOperation");
        errorResponse.setMessage("Method not allowed");

        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleDefaultExceptions(Exception ex) {
        log.error(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("UnknownError");
        errorResponse.setMessage("Internal Server Error");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
