package com.gaspar.book.handler;

import com.gaspar.book.exceptions.OperationNotPermittedException;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static com.gaspar.book.handler.BusinessErrorCode.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException exception){
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(ACCOUNT_DISABLED.getCode())
                        .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                        .error(exception.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException exception){
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(ACCOUNT_LOCKED.getCode())
                        .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                        .error(exception.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exception){
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(BAD_CREDENTIALS.getCode())
                        .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                        .error(exception.getMessage())
                        .build()
        );
    }

    //Pour une quelconque erreur
    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException exception){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                ExceptionResponse.builder()
                        .error(exception.getMessage())
                        .build()
        );
    }

    //User sent invalid data (ex: email missing), throw by @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exception){
        Set<String> errors = new HashSet<>();
        exception.getBindingResult().getAllErrors()
                .forEach(error ->{
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });
        return ResponseEntity.status(BAD_REQUEST).body(
                ExceptionResponse.builder()
                        .validationErrors(errors)
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception){
        exception.printStackTrace();
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                ExceptionResponse.builder()
                        .businessErrorDescription("Internal error, contact the admin")
                        .error(exception.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleException(OperationNotPermittedException exception){
        exception.printStackTrace();
        return ResponseEntity.status(BAD_REQUEST).body(
                ExceptionResponse.builder()
                        .error(exception.getMessage())
                        .build()
        );
    }
}
