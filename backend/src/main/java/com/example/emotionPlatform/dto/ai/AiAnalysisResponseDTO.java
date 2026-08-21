package com.example.emotionPlatform.dto.ai;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiAnalysisResponseDTO {
    private Long id;

    private Double moodScore;

    private String summary;

    private String jsonResult;
}
