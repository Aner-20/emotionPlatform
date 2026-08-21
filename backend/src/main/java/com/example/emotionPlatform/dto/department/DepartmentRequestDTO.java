package com.example.emotionPlatform.dto.department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequestDTO {
    @NotBlank
    @Size(min = 2, max = 40)
    private String name;
}
