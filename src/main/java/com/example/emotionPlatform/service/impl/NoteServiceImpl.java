package com.example.emotionPlatform.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.emotionPlatform.dto.note.NoteRequestDTO;
import com.example.emotionPlatform.dto.note.NoteResponseDTO;
import com.example.emotionPlatform.entity.Note;
import com.example.emotionPlatform.entity.User;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.exception.UnAuthorizedException;
import com.example.emotionPlatform.mapper.NoteMapper;
import com.example.emotionPlatform.repository.NoteRepository;
import com.example.emotionPlatform.repository.UserRepository;
import com.example.emotionPlatform.service.NoteService;
import com.example.emotionPlatform.entity.RoleType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

// Senza servizio AI
public class NoteServiceImpl implements NoteService {
    
    private final NoteRepository noteRepository;
    
    private final NoteMapper noteMapper;

    @Override
    public NoteResponseDTO createNote(NoteRequestDTO request, User user) {
        Note note = noteMapper.toEntity(request);
        note.setUser(user);
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());

        Note savedNote = noteRepository.save(note);

        return noteMapper.toResponse(savedNote);

    }

    @Override
    public NoteResponseDTO getNoteById(Long id, User user) {
        Note note = getNoteOrThrow(id);

        checkAccess(note, user);

        return noteMapper.toResponse(note);
    }

    @Override
    public List<NoteResponseDTO> getMyNotes(User user) {
         return noteRepository.findByUser(user)
                .stream()
                .map(noteMapper::toResponse)
                .toList();
    }

    @Override
    public List<NoteResponseDTO> getAllNotes() {
          return noteRepository.findAll()
                .stream()
                .map(noteMapper::toResponse)
                .toList();
    }

    @Override
    public NoteResponseDTO updateNote(Long id, NoteRequestDTO request, User user) {
        
        Note note = getNoteOrThrow(id);

        checkAccess(note, user);

        note.setText(request.getText());
        note.setIsPrivate(request.getIsPrivate());
        note.setUpdatedAt(LocalDateTime.now());

        Note updatedNote = noteRepository.save(note);

        return noteMapper.toResponse(updatedNote);

    }

    @Override
    public void deleteNote(Long id, User user) {
        Note note = getNoteOrThrow(id);
        checkAccess(note, user);
        noteRepository.delete(note);
    }

    private Note getNoteOrThrow(Long id){
        return noteRepository.findById(id).orElseThrow(() -> new NotFoundException("Note not found"));
    }
    
    private void checkAccess(Note note, User user){
        boolean isOwner = note.getUser()
                              .getId()
                              .equals(user.getId());

        boolean isAdmin = user.getRole().getName().equals(RoleType.ADMIN.name());

        if (!isOwner && !isAdmin){
            throw new UnAuthorizedException("You cannot access this note");
        }

    
    }

}
