package com.example.emotionPlatform.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.emotionPlatform.dto.note.NoteRequestDTO;
import com.example.emotionPlatform.dto.note.NoteResponseDTO;
import com.example.emotionPlatform.entity.User;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.repository.UserRepository;
import com.example.emotionPlatform.service.NoteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    
    private final NoteService noteService;
    
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<NoteResponseDTO> createNote(@Valid @RequestBody NoteRequestDTO request, @RequestParam Long userId){
        
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        NoteResponseDTO response = noteService.createNote(request, user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> getAllNotes(){
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> getNoteById(@PathVariable Long id, @RequestParam Long userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        return ResponseEntity.ok(noteService.getNoteById(userId, user));
        
    }

    @GetMapping("/my")
    public ResponseEntity<List<NoteResponseDTO>> getMyNotes(@RequestParam Long userId) {
        
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        
         return ResponseEntity.ok(noteService.getMyNotes(user));

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> updateNote(@PathVariable Long id, @Valid @RequestBody NoteRequestDTO request, @RequestParam Long userId){
        
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        return ResponseEntity.ok(noteService.updateNote(userId, request, user));

    }

    // @RequestParam è temporaneo

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id, @RequestParam Long userId){
        
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        noteService.deleteNote(userId, user);

        return ResponseEntity
                .noContent()
                .build();

    }

}
