package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BookNotFound.class)
    public ResponseEntity<ApiError> handleNotFound() {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, "Book not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyExists.class)
    public ResponseEntity<ApiError> handleBadRequest() {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "Book already exists");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}