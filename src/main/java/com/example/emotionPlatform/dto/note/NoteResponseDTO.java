package com.example.emotionPlatform.dto.note;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

import com.example.emotionPlatform.dto.ai.AiAnalysisResponseDTO;
import com.example.emotionPlatform.dto.noteemotion.NoteEmotionResponseDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteResponseDTO {
    private Long id;

    private String text;

    private Boolean isPrivate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<NoteEmotionResponseDTO> emotions;

    private AiAnalysisResponseDTO aiAnalysis;
}
