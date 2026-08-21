package com.example.emotionPlatform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emotionPlatform.entity.Note;
import com.example.emotionPlatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/* 
Una page contiene info come:
- content: note della pagina corrente
- number: numero della pagina
- size: elementi per pagina
- totalElements: numero totale di elementi
- totalPages: numero totale di pagine


*/


public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);

    List<Note> findByUserId(Long userId);

    Optional<Note> findByIdAndUserId(Long noteId, Long userId);

    Page<Note> findByUser(User user, Pageable pageable);
    
}
