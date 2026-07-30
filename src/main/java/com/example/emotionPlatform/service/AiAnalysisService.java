package com.example.emotionPlatform.service;

import com.example.emotionPlatform.dto.ai.AiAnalysisResponseDTO;
import com.example.emotionPlatform.entity.Note;

public interface AiAnalysisService {
    
    AiAnalysisResponseDTO createAnalysis(Note note, Double moodScore, String summary, String jsonResult);

    AiAnalysisResponseDTO getAnalysisByNote(Long noteId);

    void deleteAnalysis(Long noteId);


}
