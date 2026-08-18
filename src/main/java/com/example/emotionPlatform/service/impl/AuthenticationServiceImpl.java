package com.example.emotionPlatform.service.impl;

import org.springframework.boot.logging.structured.JsonWriterStructuredLogFormatter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.emotionPlatform.dto.auth.LoginRequestDTO;
import com.example.emotionPlatform.dto.auth.LoginResponseDTO;
import com.example.emotionPlatform.dto.auth.RegisterRequestDTO;
import com.example.emotionPlatform.dto.user.UserResponseDTO;
import com.example.emotionPlatform.entity.Department;
import com.example.emotionPlatform.entity.Role;
import com.example.emotionPlatform.entity.RoleType;
import com.example.emotionPlatform.entity.User;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.mapper.UserMapper;
import com.example.emotionPlatform.repository.DepartmentRepository;
import com.example.emotionPlatform.repository.RoleRepository;
import com.example.emotionPlatform.repository.UserRepository;
import com.example.emotionPlatform.security.JwtService;
import com.example.emotionPlatform.service.AuthenticationService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    
    

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



    @Override
    public UserResponseDTO register(RegisterRequestDTO request) {
        User user = userMapper.toEntity(request);

        Role role = roleRepository.findByName(RoleType.ADMIN).orElseThrow(() -> new NotFoundException("USER role not found"));

        user.setRole(role);

        if (request.getDepartmentId() != null) {

            Department department = departmentRepository
                    .findById(request.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException("Department not found"));

            user.setDepartment(department);
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }



}
