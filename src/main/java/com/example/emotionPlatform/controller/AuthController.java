package com.example.emotionPlatform.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emotionPlatform.dto.auth.LoginRequestDTO;
import com.example.emotionPlatform.dto.auth.LoginResponseDTO;
import com.example.emotionPlatform.dto.auth.RegisterRequestDTO;
import com.example.emotionPlatform.dto.user.UserResponseDTO;
import com.example.emotionPlatform.service.AuthenticationService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthenticationService autheticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
        @Valid @RequestBody LoginRequestDTO request
    ) 
    {


        return ResponseEntity.ok(autheticationService.login(request));
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request){
        UserResponseDTO response = autheticationService.register(request);

        return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(response);
    }

}
