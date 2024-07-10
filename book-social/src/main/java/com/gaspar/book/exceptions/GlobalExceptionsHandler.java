package com.gaspar.book.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    public ResponseEntity<ExceptionResponse> handleException(LockedException exception){

    }
}
