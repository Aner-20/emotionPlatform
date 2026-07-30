package com.example.emotionPlatform.service;

import java.util.List;

import com.example.emotionPlatform.dto.user.UserRequestDTO;
import com.example.emotionPlatform.dto.user.UserResponseDTO;

public interface UserService {
    
    UserResponseDTO createUser(UserRequestDTO request);

    UserResponseDTO findUserById(Long id);

    List<UserResponseDTO> findAllUsers();

    UserResponseDTO updateUsers(Long id, UserRequestDTO request);

    void deleteUser(Long id);
}
