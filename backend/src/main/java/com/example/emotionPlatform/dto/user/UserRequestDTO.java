package com.example.emotionPlatform.dto.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    @NotBlank(message = "Mandatory first name")
    @Size(min = 3, max = 50)
    @Pattern(
         regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+$",
         message = "First name can contain only letters"
    )
    private String firstName;

    @NotBlank(message = "Mandatory last name")
    @Size(min = 3, max = 50)
    @Pattern(
        regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+$",
        message = "Last name can contain only letters"
    )
    private String lastName;

    @NotBlank(message = "Email is required") // previene "", " ", null
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8)
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
        message = "Password must contain at least one uppercase letter, one lowercase letter and one number"
    )
    private String password;

    private Long departmentId;

    private Long roleId;
}
