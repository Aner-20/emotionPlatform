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
import org.springframework.web.bind.annotation.RestController;

import com.example.emotionPlatform.dto.note.NoteRequestDTO;
import com.example.emotionPlatform.dto.note.NoteResponseDTO;
import com.example.emotionPlatform.entity.User;
import com.example.emotionPlatform.service.NoteService;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NoteResponseDTO> getNoteById(@PathVariable Long id, Authentication authentication){

        //User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(noteService.getNoteById(id, user));
        
    }

   
    
    
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NoteResponseDTO> updateNote(@PathVariable Long id, @Valid @RequestBody NoteRequestDTO request, Authentication authentication){
        
        //User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(noteService.updateNote(id, request, user));

    }

    // @RequestParam è temporaneo
   
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id, Authentication authentication){
        
        //User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        User user = (User) authentication.getPrincipal();

        noteService.deleteNote(id, user);

        return ResponseEntity
                .noContent()
                .build();

    }

    /* 
    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<NoteResponseDTO>> getMyNotes(Authentication authentication) {
        
        //User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(noteService.getMyNotes(user));

    }*/ 

    // Con pageable
    // @ParameterObject tratta Pageable come un insieme di parametri quert (page, size, sort) non come un unico oggetto


    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<NoteResponseDTO>> getMyNotes(Authentication authentication, @ParameterObject Pageable pageable){
        User user = (User) authentication.getPrincipal(); 
        System.out.println("PAGEABLE: " + pageable);
        return ResponseEntity.ok(noteService.getMyNotes(user, pageable));
    }


    @GetMapping("/my/{id}")
    public ResponseEntity<NoteResponseDTO> getMyNote(@PathVariable Long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(noteService.getMyNoteById(id, user));
    }

    @PutMapping("/my/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<NoteResponseDTO> updateMyNote(@PathVariable Long id, @RequestBody NoteRequestDTO request, Authentication authentication){
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(noteService.updateMyNote(id, request, user));
    }

    @DeleteMapping("/my/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> deleteMyNote(@PathVariable Long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();

        noteService.deleteMyNote(id, user);

        return ResponseEntity
                .noContent()
                .build();
    }

}
