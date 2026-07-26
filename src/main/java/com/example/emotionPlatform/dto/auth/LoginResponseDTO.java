package com.example.emotionPlatform.dto.auth;

import com.example.emotionPlatform.dto.user.UserResponseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private String token;

    private UserResponseDTO user;
}
