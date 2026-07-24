package com.example.emotionPlatform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

@Entity
@Table(name = "note_emotions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class NoteEmotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id", nullable = false)
    private Note note;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_id", nullable = false)
    private Emotion emotion;


    @Column(nullable = false)
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double score;
}
