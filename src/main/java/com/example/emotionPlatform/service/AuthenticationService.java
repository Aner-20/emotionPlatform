package com.example.emotionPlatform.service;

import com.example.emotionPlatform.dto.auth.LoginRequestDTO;
import com.example.emotionPlatform.dto.auth.LoginResponseDTO;

public interface AuthenticationService {
    
    LoginResponseDTO login(LoginRequestDTO request);
}
