package com.example.emotionPlatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.emotionPlatform.dto.note.NoteRequestDTO;
import com.example.emotionPlatform.dto.note.NoteResponseDTO;
import com.example.emotionPlatform.entity.Note;

@Mapper(componentModel = "spring", uses = {NoteEmotionMapper.class, AiAnalysisMapper.class})
public interface NoteMapper {
    
    Note toEntity(NoteRequestDTO dto);
    // @Mapping: il campo emotions del DTO deve essere preso dal campo noteEmotions dell'entity.
    // Si usa quando i nomi non coincidono in Entity e in DTO
     @Mapping(
        source = "noteEmotions",
        target = "emotions"
    )
    NoteResponseDTO toResponse(Note note);
}
