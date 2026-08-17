package com.example.emotionPlatform.service.impl;

import org.springframework.boot.logging.structured.JsonWriterStructuredLogFormatter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.emotionPlatform.dto.auth.LoginRequestDTO;
import com.example.emotionPlatform.dto.auth.LoginResponseDTO;
import com.example.emotionPlatform.entity.User;
import com.example.emotionPlatform.mapper.UserMapper;
import com.example.emotionPlatform.security.JwtService;
import com.example.emotionPlatform.service.AuthenticationService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    
    @PostConstruct
    public void generateAdminPassword(){
        System.out.println("Admin123");
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        // dice a spring security di verificare se l'email e la password sono corrette
        var authentication = authenticationManager.authenticate(
            // richiesta di autenticazione
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // In authenticationManager:
        // spring recupera gli userDetails e poi confronta la password ricevuta con quella nel db
        // se la password è corretta (lo si verifica tramite passwordEncoder): l'autenticazione avviene con successo

        // si prende l'utente che ha appena autenticato
        // getPrincipal() contiene gli UserDetails
        // Senza casting (user) non si potrebbe fare ad esempio user.getEmail(), in quanto Object non contiene il metodo getEmail()
        User user = (User) authentication.getPrincipal();

        String token = jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                  .token(token)
                  .user(userMapper.toResponse(user))
                  .build();
    }



}
