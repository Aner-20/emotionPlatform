package com.example.emotionPlatform.dto.user;

import com.example.emotionPlatform.dto.department.DepartmentResponseDTO;
import com.example.emotionPlatform.dto.role.RoleResponseDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private RoleResponseDTO role;

    private DepartmentResponseDTO department;
}
