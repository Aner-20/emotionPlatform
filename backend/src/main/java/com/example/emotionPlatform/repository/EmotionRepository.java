package com.example.emotionPlatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emotionPlatform.entity.Emotion;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    
    Optional<Emotion> findByName(String name);
}
