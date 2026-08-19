package com.example.emotionPlatform.service;

import com.example.emotionPlatform.dto.ai.AiPythonResponse;

public interface AiClientService {

    public AiPythonResponse analyze(String text);
    
}
