package com.example.emotionPlatform.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.emotionPlatform.dto.ai.AiAnalysisRequestDTO;
import com.example.emotionPlatform.dto.ai.AiPythonResponse;
import com.example.emotionPlatform.service.AiClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiClientServiceImpl implements AiClientService {
    
    // RestClient fa la chiamata a python
    private final RestClient restClient;

    @Override
    public AiPythonResponse analyze(String text) {
        AiAnalysisRequestDTO request = AiAnalysisRequestDTO.builder()
               .text(text)
               .build();

        return restClient
                .post() // richiesta HTTP POST
                .uri("/analyze") // endpoint da chiamare
                .body(request) // il testo che manda
                .retrieve() // invia e recupera la risposta
                .body(AiPythonResponse.class); // la risposta ricevuta da python viene trasformata in un oggetto java AiPythonResponse
    }

    

}
