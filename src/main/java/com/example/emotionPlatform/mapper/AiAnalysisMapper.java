package com.example.emotionPlatform.mapper;

import org.mapstruct.Mapper;

import com.example.emotionPlatform.dto.ai.AiAnalysisResponseDTO;
import com.example.emotionPlatform.entity.AiAnalysis;

@Mapper(componentModel = "spring")
public interface AiAnalysisMapper {
    AiAnalysisResponseDTO toResponse(AiAnalysis aiAnalysis);
}
