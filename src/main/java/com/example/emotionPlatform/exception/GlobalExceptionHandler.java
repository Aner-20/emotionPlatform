package com.example.emotionPlatform.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.emotionPlatform.dto.error.ErrorResponse;

// @RestControllerAdvice: intercetta tutte le eccezioni in tutta l'applicazione
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
        NotFoundException exception
    ) {
        
         ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                404,
                exception.getMessage()
        );


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);

    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(
            UnAuthorizedException exception
    ){

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                401,
                exception.getMessage()
        );


        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            ValidationException exception
    ){

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                400,
                exception.getMessage()
        );


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}
