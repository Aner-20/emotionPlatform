package com.example.emotionPlatform.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    // @JdbcTypeCode dice a Hibernate di leggere e trattare l'attributo come un valore JSON
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "json_result", columnDefinition = "jsonb")
    private String jsonResult;


    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
