package com.example.emotionPlatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.emotionPlatform.dto.department.DepartmentRequestDTO;
import com.example.emotionPlatform.dto.department.DepartmentResponseDTO;
import com.example.emotionPlatform.entity.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    
    // ignore ignora i campi dell'entity Department
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    Department toEntity(DepartmentRequestDTO dto);
    
    
    DepartmentResponseDTO toResponse(Department department);
}
