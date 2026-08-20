package com.example.emotionPlatform.service.impl;

import org.springframework.security.access.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Locale; // utile per conversioni di testo indipendenti dalla lingua locale della macchina

import org.springframework.stereotype.Service;

import com.example.emotionPlatform.dto.ai.AiPythonResponse;
import com.example.emotionPlatform.dto.note.NoteRequestDTO;
import com.example.emotionPlatform.dto.note.NoteResponseDTO;
import com.example.emotionPlatform.entity.AiAnalysis;
import com.example.emotionPlatform.entity.Note;
import com.example.emotionPlatform.entity.User;
import com.example.emotionPlatform.exception.NotFoundException;
import com.example.emotionPlatform.exception.UnAuthorizedException;
import com.example.emotionPlatform.mapper.NoteMapper;
import com.example.emotionPlatform.repository.AiAnalysisRepository;
import com.example.emotionPlatform.repository.EmotionRepository;
import com.example.emotionPlatform.repository.NoteRepository;
import com.example.emotionPlatform.service.AiClientService;
import com.example.emotionPlatform.service.NoteService;
import com.example.emotionPlatform.entity.RoleType;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;


import com.example.emotionPlatform.entity.Emotion;
import com.example.emotionPlatform.entity.NoteEmotion;

// ObjectMapper converte Map in una vera stringa JSON

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    
    private final NoteRepository noteRepository;
    private final AiAnalysisRepository aiAnalysisRepository;
    private final EmotionRepository emotionRepository;
    private final NoteMapper noteMapper;
    private final ObjectMapper objectMapper;
    private final AiClientService aiClientService;

    @Override
    public NoteResponseDTO createNote(NoteRequestDTO request, User user) {
        Note note = noteMapper.toEntity(request);
        note.setUser(user);
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());

        Note savedNote = noteRepository.save(note);

        AiPythonResponse aiResponse = aiClientService.analyze(note.getText());
        Map<String, Object> emotions = (Map<String, Object>) aiResponse.getJsonResult().get("emotions");
        
        
        for (Map.Entry<String, Object> entry : emotions.entrySet()){
            
            // Locale.ROOT converte il testo senza basarsi sulla lignua locale del computer
            String emotionName = entry.getKey().toUpperCase(Locale.ROOT); // le emozioni nel db sono in stampatello maiuscolo
            
            System.out.println("EMOTION NAME: " + emotionName);
            double rawScore = ((Number) entry.getValue()).doubleValue();
            Double score = Math.round(rawScore * 10000.0) / 100.0;
            score = Math.max(0.0, Math.min(score, 100.0));
            System.out.println("Raw score: " + rawScore);
            System.out.println("Score: " + score);
            Emotion emotion = emotionRepository.findByName(emotionName).orElseThrow(() -> new RuntimeException("Emotion not found in database: " + emotionName));
            
            NoteEmotion noteEmotion = NoteEmotion.builder()
                .note(savedNote)
                .emotion(emotion)
                .score(score)
                .build();

            savedNote.getNoteEmotions().add(noteEmotion);

        }
        
        AiAnalysis aiAnalysis = AiAnalysis.builder()
                     .note(savedNote)
                     .moodScore(aiResponse.getMoodScore())
                     .summary(aiResponse.getSummary())
                     .jsonResult(objectMapper.writeValueAsString(aiResponse.getJsonResult()))
                     .createdAt(LocalDateTime.now())
                     .build();
        
        aiAnalysisRepository.save(aiAnalysis);
        
        savedNote.setAiAnalysis(aiAnalysis);
        //System.out.println("MOOD SCORE: " + aiAnalysis.getMoodScore());
        //System.out.println("SUMMARY: " + aiAnalysis.getSummary());
        //System.out.println("JSON RESULT: " + aiAnalysis.getJsonResult());

        return noteMapper.toResponse(savedNote); 

    }

    @Override
    public NoteResponseDTO getNoteById(Long id, User user) {
        Note note = getNoteOrThrow(id);

        checkAccess(note, user);

        return noteMapper.toResponse(note);
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



    @Override
    public NoteResponseDTO getMyNote(Long noteId, User user){
        Note note = noteRepository.findByIdAndUserId(noteId, user.getId()).orElseThrow(() -> new NotFoundException("Note not found"));
        return noteMapper.toResponse(note);
    }

    @Override
    public NoteResponseDTO updateMyNote(Long noteId, NoteRequestDTO request, User user){
        Note note = noteRepository.findByIdAndUserId(noteId, user.getId()).orElseThrow(() -> new NotFoundException("Note not found"));
        
        note.setText(request.getText());
        note.setIsPrivate(request.getIsPrivate());
        note.setUpdatedAt(LocalDateTime.now());

        Note updatedNote = noteRepository.save(note);

        return noteMapper.toResponse(updatedNote);
    }
    

    @Override
    public List<NoteResponseDTO> getMyNotes(User user) {
         return noteRepository.findByUser(user)
                .stream()
                .map(noteMapper::toResponse)
                .toList();
    }

    public void deleteMyNote(Long id, User user){
        Note note = getNoteOrThrow(id);

        if (!note.getUser().getId().equals(user.getId())){
            throw new AccessDeniedException("You cannot delete this note");
        }

        noteRepository.delete(note);
    }


    private Note getNoteOrThrow(Long id){
        return noteRepository.findById(id).orElseThrow(() -> new NotFoundException("Note not found"));
    }

    private void checkAccess(Note note, User user){
        boolean isOwner = note.getUser()
                              .getId()
                              .equals(user.getId());

        //boolean isAdmin = user.getRole().getName().equals(RoleType.ADMIN.name());

        boolean isAdmin = user.getRole().getName() == RoleType.ADMIN;

        if (!isOwner && !isAdmin){
            throw new UnAuthorizedException("You cannot access this note");
        }

    
    }

}
