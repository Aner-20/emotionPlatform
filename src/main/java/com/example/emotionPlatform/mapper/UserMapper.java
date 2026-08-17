package com.example.emotionPlatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.emotionPlatform.dto.auth.RegisterRequestDTO;
import com.example.emotionPlatform.dto.user.UserRequestDTO;
import com.example.emotionPlatform.dto.user.UserResponseDTO;
import com.example.emotionPlatform.entity.User;
import com.example.emotionPlatform.utils.StringUtilsCustom;

@Mapper(
    componentModel = "spring", 
    uses = {RoleMapper.class, DepartmentMapper.class}, 
    imports = StringUtilsCustom.class )

public interface UserMapper {

    // expression esegue codice java personalizzato durante il mapping
    @Mapping(
        target = "firstName",
        expression = "java(StringUtilsCustom.capitalizeWords(dto.getFirstName()))"
    )
    @Mapping(
        target = "lastName",
        expression = "java(StringUtilsCustom.capitalizeWords(dto.getLastName()))"
    )
    User toEntity(UserRequestDTO dto);

    @Mapping(
        target = "firstName",
        expression = "java(StringUtilsCustom.capitalizeWords(dto.getFirstName()))"
    )
    @Mapping(
        target = "lastName",
        expression = "java(StringUtilsCustom.capitalizeWords(dto.getLastName()))"
    )
    User toEntity(RegisterRequestDTO dto);
    
    UserResponseDTO toResponse(User user);
}
