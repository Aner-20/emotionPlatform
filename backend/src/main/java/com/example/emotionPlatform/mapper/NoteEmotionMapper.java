package com.example.emotionPlatform.mapper;

import com.example.emotionPlatform.dto.noteemotion.NoteEmotionResponseDTO;
import com.example.emotionPlatform.entity.NoteEmotion;
import org.mapstruct.Mapper;

// uses = EmotionMapper.class: per la conversione di alcuni campi di NoteEmotion, usa anche EmotionMapper
/*
Dietro le quinte: 
NoteEmotionResponseDTO dto = new NoteEmotionResponseDTO();

dto.setId(noteEmotion.getId());

dto.setScore(noteEmotion.getScore());

dto.setEmotion(
    emotionMapper.toResponse(noteEmotion.getEmotion())
);

return dto;

*/
@Mapper(componentModel = "spring", uses = EmotionMapper.class)
public interface NoteEmotionMapper {
    NoteEmotionResponseDTO toResponse(NoteEmotion noteEmotion);
}
