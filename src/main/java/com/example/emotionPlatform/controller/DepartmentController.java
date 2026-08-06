package com.example.emotionPlatform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.emotionPlatform.dto.department.DepartmentRequestDTO;
import com.example.emotionPlatform.dto.department.DepartmentResponseDTO;
import com.example.emotionPlatform.service.DepartmentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import java.util.*;

@RestController
@RequestMapping("api/departments")
@RequiredArgsConstructor

public class DepartmentController {
    
    private final DepartmentService departmentService;

    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponseDTO> createDeparment(@Valid @RequestBody DepartmentRequestDTO request) {

        DepartmentResponseDTO response = departmentService.createDepartment(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {

        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

  
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable Long id){
        // ok() crea una risposta HTTP con status 200 ok
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

  
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO request
    ) {

        return ResponseEntity.ok(departmentService.updateDepartment(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity
                .noContent() // crea risposta con status 204 NO CONTENT
                .build(); // costruisce l'oggetto in modo definitivo
    }

}
