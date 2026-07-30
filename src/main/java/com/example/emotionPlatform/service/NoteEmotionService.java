package com.example.emotionPlatform.service;

import java.util.List;

import com.example.emotionPlatform.dto.noteemotion.NoteEmotionResponseDTO;

public interface NoteEmotionService {
    
    List<NoteEmotionResponseDTO> getEmotionsByNote(Long noteId);

    NoteEmotionResponseDTO saveNoteEmotion(Long noteId, Long emotionId, Double score);

    void deleteByNote(Long noteId);

}
