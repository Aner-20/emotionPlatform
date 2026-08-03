package com.example.emotionPlatform.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.emotionPlatform.dto.noteemotion.NoteEmotionResponseDTO;
import com.example.emotionPlatform.service.NoteEmotionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/note-emotions")
@RequiredArgsConstructor
public class NoteEmotionController {
    
    private final NoteEmotionService noteEmotionService;
    
    @PostMapping
    public ResponseEntity<NoteEmotionResponseDTO> saveNoteEmotion(@RequestParam Long noteId, @RequestParam Long emotionId, @RequestParam Double score){

        NoteEmotionResponseDTO response = noteEmotionService.saveNoteEmotion(noteId, emotionId, score);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);


    }

    @GetMapping("/note/{noteId}")
    public ResponseEntity<List<NoteEmotionResponseDTO>> getEmotionsByNote(@PathVariable Long noteId){
        return ResponseEntity.ok(noteEmotionService.getEmotionsByNote(noteId));
    }

    @DeleteMapping("/note/{noteId}")
    public ResponseEntity<Void> deleteByNote(@PathVariable Long noteId){
        noteEmotionService.deleteByNote(noteId);

        return ResponseEntity
                .noContent()
                .build();
    }


}
