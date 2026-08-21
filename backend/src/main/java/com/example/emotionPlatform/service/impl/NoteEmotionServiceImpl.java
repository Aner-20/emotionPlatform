package com.example.emotionPlatform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.emotionPlatform.dto.noteemotion.NoteEmotionResponseDTO;
import com.example.emotionPlatform.entity.Emotion;
import com.example.emotionPlatform.entity.Note;
import com.example.emotionPlatform.entity.NoteEmotion;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.mapper.NoteEmotionMapper;
import com.example.emotionPlatform.repository.EmotionRepository;
import com.example.emotionPlatform.repository.NoteEmotionRepository;
import com.example.emotionPlatform.repository.NoteRepository;
import com.example.emotionPlatform.service.NoteEmotionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteEmotionServiceImpl implements NoteEmotionService {
    
    private final NoteEmotionRepository noteEmotionRepository;
    private final NoteRepository noteRepository;
    private final EmotionRepository emotionRepository;

    private final NoteEmotionMapper noteEmotionMapper;

     @Override
    public List<NoteEmotionResponseDTO> getEmotionsByNote(Long noteId) {

        Note note = getNoteOrThrow(noteId);

        return noteEmotionRepository.findByNote(note)
                .stream()
                .map(noteEmotionMapper::toResponse)
                .toList();

    }

    @Override
    public NoteEmotionResponseDTO saveNoteEmotion(Long noteId, Long emotionId, Double score){

        Note note = getNoteOrThrow(noteId);

        Emotion emotion = emotionRepository.findById(emotionId).orElseThrow(() -> new NotFoundException("Emotion not found"));
    
        
        NoteEmotion noteEmotion = NoteEmotion.builder()
                .note(note)
                .emotion(emotion)
                .score(score)
                .build();

        NoteEmotion saved = noteEmotionRepository.save(noteEmotion);

        return noteEmotionMapper.toResponse(saved);

    }

    @Override
    public void deleteByNote(Long noteId){
        Note note = getNoteOrThrow(noteId);

        noteEmotionRepository.deleteByNote(note);

    }

    private Note getNoteOrThrow(Long id) {

        return noteRepository.findById(id).orElseThrow(() -> new NotFoundException("Note not found"));
    }
}
