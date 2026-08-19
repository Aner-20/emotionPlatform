package com.example.emotionPlatform.dto.ai;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiPythonResponse {
    private Double moodScore;

    private String summary;

    // Jsonnode permette di ricevere json senza dover creare una serie di classi java per rappresentare tutte le emozioni
    private JsonNode jsonResult;
}
