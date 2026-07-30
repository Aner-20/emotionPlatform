package com.example.emotionPlatform.service;

import java.util.List;

import com.example.emotionPlatform.dto.emotion.EmotionResponseDTO;

public interface EmotionService {
    EmotionResponseDTO getEmotionById(Long id);

    List<EmotionResponseDTO> getAllEmotions();
}
