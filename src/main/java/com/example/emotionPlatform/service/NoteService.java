package com.example.emotionPlatform.service;

import java.util.List;

import com.example.emotionPlatform.dto.note.NoteRequestDTO;
import com.example.emotionPlatform.dto.note.NoteResponseDTO;
import com.example.emotionPlatform.entity.User;


// l'utente è già autenticato tramite JWT.
// l' userId viene recuperato dal JWT e passato come User
public interface NoteService {
    
    NoteResponseDTO createNote(NoteRequestDTO request, User user);

    NoteResponseDTO getNoteById(Long id, User user);

    List<NoteResponseDTO> getMyNotes(User user);

    List<NoteResponseDTO> getAllNotes();

    NoteResponseDTO updateNote(Long id, NoteRequestDTO request, User user);

    void deleteNote(Long id, User user);

}
