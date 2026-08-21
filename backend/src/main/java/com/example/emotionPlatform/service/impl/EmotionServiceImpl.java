package com.example.emotionPlatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.emotionPlatform.dto.emotion.EmotionResponseDTO;
import com.example.emotionPlatform.entity.Emotion;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.mapper.EmotionMapper;
import com.example.emotionPlatform.repository.EmotionRepository;
import com.example.emotionPlatform.service.EmotionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmotionServiceImpl implements EmotionService {
    private final EmotionRepository emotionRepository;
    private final EmotionMapper emotionMapper;
    
    @Override
    public EmotionResponseDTO getEmotionById(Long id) {
        Emotion emotion = emotionRepository.findById(id).orElseThrow(() -> new NotFoundException("Emotion not found"));
        return emotionMapper.toResponse(emotion);
    
    }
    
    @Override
    public List<EmotionResponseDTO> getAllEmotions() {
          return emotionRepository.findAll()
                .stream()
                .map(emotionMapper::toResponse)
                .toList();
    }
}
