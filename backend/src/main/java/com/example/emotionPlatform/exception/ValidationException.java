package com.example.emotionPlatform.exception;

// Serve per errori logici

public class ValidationException extends RuntimeException {
    
    public ValidationException(String message){
        super(message);
    }
}
