package com.example.emotionPlatform.mapper;

import org.mapstruct.Mapper;

import com.example.emotionPlatform.dto.emotion.EmotionResponseDTO;
import com.example.emotionPlatform.entity.Emotion;

@Mapper(componentModel = "spring")
public interface EmotionMapper {
    EmotionResponseDTO toResponse(Emotion emotion);
}
