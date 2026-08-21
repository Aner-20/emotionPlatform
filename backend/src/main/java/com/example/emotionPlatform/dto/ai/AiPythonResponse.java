package com.example.emotionPlatform.dto.ai;

import tools.jackson.databind.JsonNode;

import java.util.Map;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString // genera in automatico il metodo toString()
public class AiPythonResponse {
    private Double moodScore;

    private String summary;

    // Jsonnode permette di ricevere json senza dover creare una serie di classi java per rappresentare tutte le emozioni
    //private JsonNode jsonResult;
    private Map<String, Object> jsonResult;
    // Si usa Map perchè la risposta di python è un dizionario
}
