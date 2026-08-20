package com.example.emotionPlatform.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    
    //private final UserRepository userRepository;

    
    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<NoteResponseDTO> createNote(@Valid @RequestBody NoteRequestDTO request, Authentication authentication){
        
        //User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        User user = (User) authentication.getPrincipal();

        NoteResponseDTO response = noteService.createNote(request, user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }
    
   
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<NoteResponseDTO>> getAllNotes(){
        return ResponseEntity.ok(noteService.getAllNotes());
    }

   
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<NoteResponseDTO> getNoteById(@PathVariable Long id, Authentication authentication){

        //User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(noteService.getNoteById(id, user));
        
    }

   
    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<NoteResponseDTO>> getMyNotes(Authentication authentication) {
        
        //User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(noteService.getMyNotes(user));

    }
    
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<NoteResponseDTO> updateNote(@PathVariable Long id, @Valid @RequestBody NoteRequestDTO request, Authentication authentication){
        
        //User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(noteService.updateNote(id, request, user));

    }

    // @RequestParam è temporaneo
   
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id, Authentication authentication){
        
        //User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        User user = (User) authentication.getPrincipal();

        noteService.deleteNote(id, user);

        return ResponseEntity
                .noContent()
                .build();

    }

}
