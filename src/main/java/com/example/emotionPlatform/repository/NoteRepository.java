package com.example.emotionPlatform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emotionPlatform.entity.Note;
import com.example.emotionPlatform.entity.User;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);

    List<Note> findByUserId(Long userId);

    Optional<Note> findByIdAndUserId(Long noteId, Long userId);
    
}
