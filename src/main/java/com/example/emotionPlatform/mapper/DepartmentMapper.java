package com.example.emotionPlatform.mapper;

import org.mapstruct.Mapper;

import com.example.emotionPlatform.dto.department.DepartmentRequestDTO;
import com.example.emotionPlatform.dto.department.DepartmentResponseDTO;
import com.example.emotionPlatform.entity.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department toEntity(DepartmentRequestDTO dto);
    DepartmentResponseDTO toResponse(Department department);
}
