package com.example.emotionPlatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.emotionPlatform.dto.department.DepartmentRequestDTO;
import com.example.emotionPlatform.dto.department.DepartmentResponseDTO;
import com.example.emotionPlatform.entity.Department;
import com.example.emotionPlatform.utils.StringUtilsCustom;

@Mapper(componentModel = "spring", imports = StringUtilsCustom.class)
public interface DepartmentMapper {
    
    // ignore ignora i campi dell'entity Department
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(
        target = "name",
        expression = "java(StringUtilsCustom.capitalizeWords(dto.getName()))"
    )
    Department toEntity(DepartmentRequestDTO dto);
    
    
    DepartmentResponseDTO toResponse(Department department);
}
