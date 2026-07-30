package com.example.emotionPlatform.service;

import java.util.List;

import com.example.emotionPlatform.dto.role.RoleResponseDTO;

public interface RoleService {
    
    RoleResponseDTO getRoleById(Long id);

    List<RoleResponseDTO> getAllRoles();
}
