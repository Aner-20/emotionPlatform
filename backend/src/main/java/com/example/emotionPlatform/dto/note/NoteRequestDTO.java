package com.example.emotionPlatform.dto.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteRequestDTO {
    @NotBlank(message = "Il testo è obbligatorio")
    @Size(max = 5000, message = "Il testo è troppo lungo")
    private String text;

    private Boolean isPrivate;

}
