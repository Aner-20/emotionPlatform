package com.example.emotionPlatform.mapper;

import org.mapstruct.Mapper;

import com.example.emotionPlatform.dto.user.UserRequestDTO;
import com.example.emotionPlatform.dto.user.UserResponseDTO;
import com.example.emotionPlatform.entity.User;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, DepartmentMapper.class} )
public interface UserMapper {
    User toEntity(UserRequestDTO dto);
    
    UserResponseDTO toResponse(User user);
}
