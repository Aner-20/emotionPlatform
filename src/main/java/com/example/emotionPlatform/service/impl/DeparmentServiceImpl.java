package com.example.emotionPlatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.emotionPlatform.dto.department.DepartmentRequestDTO;
import com.example.emotionPlatform.dto.department.DepartmentResponseDTO;
import com.example.emotionPlatform.entity.Department;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.mapper.DepartmentMapper;
import com.example.emotionPlatform.repository.DepartmentRepository;
import com.example.emotionPlatform.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeparmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO request) {
        Department department = departmentMapper.toEntity(request);

        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {
         Department department = getDepartmentOrThrow(id);

        return departmentMapper.toResponse(department);
    }

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO request) {
        Department department = getDepartmentOrThrow(id);
        department.setName(request.getName());

        Department updatedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) { 
        Department department = getDepartmentOrThrow(id);
        departmentRepository.delete(department);
    }

    private Department getDepartmentOrThrow(Long id){
        return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department not found"));
    }
    
}
