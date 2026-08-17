package com.example.emotionPlatform.service;

import com.example.emotionPlatform.dto.auth.LoginRequestDTO;
import com.example.emotionPlatform.dto.auth.LoginResponseDTO;
import com.example.emotionPlatform.dto.auth.RegisterRequestDTO;
import com.example.emotionPlatform.dto.user.UserResponseDTO;
public interface AuthenticationService {
    
    LoginResponseDTO login(LoginRequestDTO request);

    UserResponseDTO register(RegisterRequestDTO request);

}
