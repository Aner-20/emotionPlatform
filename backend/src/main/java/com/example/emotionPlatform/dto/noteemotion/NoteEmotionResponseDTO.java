package com.example.emotionPlatform.dto.noteemotion;

import com.example.emotionPlatform.dto.emotion.EmotionResponseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class NoteEmotionResponseDTO {
    private EmotionResponseDTO emotion;

    private Double score;
}
