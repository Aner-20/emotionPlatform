package com.example.emotionPlatform.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ai_analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiAnalysis {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id", nullable = false, unique = true)
    private Note note;


    @Column(name = "mood_score")
    private Double moodScore;


    @Column(columnDefinition = "TEXT")
    private String summary;

    // columnDefinition Si dice a Hibernate che questa colonna dev'essere di tipo jsonb
    @Column(columnDefinition = "jsonb")
    private String jsonResult;


    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
