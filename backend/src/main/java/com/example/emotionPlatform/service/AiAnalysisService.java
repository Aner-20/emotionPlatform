package com.example.emotionPlatform.service;

import com.example.emotionPlatform.dto.ai.AiAnalysisResponseDTO;
import com.example.emotionPlatform.entity.Note;

public interface AiAnalysisService {
    
    //AiAnalysisResponseDTO analyzeNote(Long noteId);

    AiAnalysisResponseDTO createAnalysis(Note note);

    AiAnalysisResponseDTO getAnalysisByNote(Long noteId);

    void deleteAnalysis(Long noteId);


}
