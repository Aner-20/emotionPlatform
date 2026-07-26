package com.example.emotionPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emotionPlatform.entity.NoteEmotion;

public interface NoteEmotionRepository extends JpaRepository<NoteEmotion, Long> {
    

    List<NoteEmotion> findByNoteId(Long noteId);


    List<NoteEmotion> findByEmotionId(Long emotionId);
    
}
