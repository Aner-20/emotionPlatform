package com.example.emotionPlatform.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 50)
    private String lastName;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8)
    private String password;

    private Long departmentId;
}
