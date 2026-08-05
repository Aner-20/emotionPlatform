package com.example.emotionPlatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.emotionPlatform.dto.user.UserRequestDTO;
import com.example.emotionPlatform.dto.user.UserResponseDTO;
import com.example.emotionPlatform.entity.Department;
import com.example.emotionPlatform.entity.Role;
import com.example.emotionPlatform.entity.User;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.mapper.UserMapper;
import com.example.emotionPlatform.repository.DepartmentRepository;
import com.example.emotionPlatform.repository.RoleRepository;
import com.example.emotionPlatform.repository.UserRepository;
import com.example.emotionPlatform.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final UserMapper userMapper;
    

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
       Role role = roleRepository.findById(request.getRoleId()).orElseThrow(() -> new NotFoundException("Role not found"));
       
       Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new NotFoundException("Department not found"));
    
       User user = userMapper.toEntity(request);
       user.setRole(role);
       user.setDepartment(department);
       User savedUser = userRepository.save(user);
        
       return userMapper.toResponse(savedUser);

    }

    @Override
    public UserResponseDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(() -> new NotFoundException("Role not found"));

        Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new NotFoundException("Department not found"));
        
        user.setRole(role);
        user.setDepartment(department);

        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);

    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }

    

}
