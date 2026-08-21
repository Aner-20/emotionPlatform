package com.example.emotionPlatform.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.emotionPlatform.dto.ai.AiAnalysisResponseDTO;
import com.example.emotionPlatform.entity.AiAnalysis;
import com.example.emotionPlatform.entity.Note;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.mapper.AiAnalysisMapper;
import com.example.emotionPlatform.repository.AiAnalysisRepository;
import com.example.emotionPlatform.service.AiAnalysisService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiAnalysisServiceImpl implements AiAnalysisService {
    
    private final AiAnalysisRepository aiAnalysisRepository;
    private final AiAnalysisMapper aiAnalysisMapper;

    @Override
    public AiAnalysisResponseDTO createAnalysis(Note note) {
        
         /*
         * Qui in futuro Java chiamerà Python.
         *
         * Python riceverà il testo della note e restituirà:
         * - moodScore
         * - summary
         * - jsonResult
         *
         * Per ora lasciamo dei valori temporanei.
         */

        Double moodScore = null;
        String summary = null;
        String jsonResult = null;
        
        AiAnalysis analysis = AiAnalysis.builder()
                .note(note)
                .moodScore(moodScore)
                .summary(summary)
                .jsonResult(jsonResult)
                .createdAt(LocalDateTime.now())
                .build();


        AiAnalysis savedAnalysis = aiAnalysisRepository.save(analysis);

        return aiAnalysisMapper.toResponse(savedAnalysis);
    }

    @Override
    public AiAnalysisResponseDTO getAnalysisByNote(Long noteId) {
       AiAnalysis analysis = aiAnalysisRepository.findByNoteId(noteId).orElseThrow(() -> new NotFoundException("AI analysis not found"));
       return aiAnalysisMapper.toResponse(analysis);
    }

    @Override
    public void deleteAnalysis(Long noteId) {
        AiAnalysis analysis = aiAnalysisRepository.findByNoteId(noteId).orElseThrow(() -> new NotFoundException("AI analysis not found"));
        aiAnalysisRepository.delete(analysis);
    }
    
}
