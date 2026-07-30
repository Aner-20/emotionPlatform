package com.example.emotionPlatform.service.impl;

import com.example.emotionPlatform.dto.role.RoleResponseDTO;
import com.example.emotionPlatform.entity.Role;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.mapper.RoleMapper;
import com.example.emotionPlatform.repository.RoleRepository;
import com.example.emotionPlatform.service.RoleService;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    

    @Override
    public RoleResponseDTO getRoleById(Long id) {

        Role role = getRoleOrThrow(id);

        return roleMapper.toResponse(role);
    }


    @Override
    public List<RoleResponseDTO> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponse)
                .toList();
    }


    private Role getRoleOrThrow(Long id){
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
    }

}
