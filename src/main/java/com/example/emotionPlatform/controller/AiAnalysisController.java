package com.example.emotionPlatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.emotionPlatform.dto.ai.AiAnalysisResponseDTO;
import com.example.emotionPlatform.entity.Note;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.repository.NoteRepository;
import com.example.emotionPlatform.service.AiAnalysisService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ai-analysis")
@RequiredArgsConstructor
public class AiAnalysisController {
    
    private final AiAnalysisService aiAnalysisService;
    
    private final NoteRepository noteRepository;


    @PostMapping
    public ResponseEntity<AiAnalysisResponseDTO> createAnalysis(@RequestParam Long noteId, @RequestParam Double moodScore, @RequestParam String summary, @RequestParam String jsonResult){

        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NotFoundException("Note not found"));

        AiAnalysisResponseDTO response = aiAnalysisService.createAnalysis(note, moodScore, summary, jsonResult);

        return ResponseEntity 
                 .status(HttpStatus.CREATED)
                 .body(response);

    }

    @GetMapping("/note/{noteId}")
    public ResponseEntity<AiAnalysisResponseDTO> getAnalysisByNote(@PathVariable Long noteId){
        return ResponseEntity.ok(aiAnalysisService.getAnalysisByNote(noteId));

    }

    @DeleteMapping("/note/{noteId}")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long noteId){
        aiAnalysisService.deleteAnalysis(noteId);

        return ResponseEntity
                .noContent()
                .build();
    }

}
