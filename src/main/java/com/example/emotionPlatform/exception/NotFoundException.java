package com.example.emotionPlatform.exception;

// Se una risorsa non esiste

public class NotFoundException extends RuntimeException {
    
    public NotFoundException(String message){
        super(message);
    }
}
