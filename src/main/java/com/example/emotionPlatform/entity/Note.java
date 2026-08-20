package com.example.emotionPlatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;


    @Column(name = "is_private")
    private Boolean isPrivate;


    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    // @JoinTable specifica la tabella di collegamento tra note e emotions
    // @JoinColumns indica la colonna della tabella intermedia che punta alla tabella notes
    // @InverseJoinColumns indica la colonna della tabella intermedia che punta ala tabella emotions
    // @Builder.Default assegna valori di default ai campi
    @Builder.Default 
    @OneToMany(
        mappedBy = "note",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    
    private List<NoteEmotion> noteEmotions = new ArrayList<>();

    @OneToOne(
            mappedBy = "note",
            cascade = CascadeType.ALL
    )
    private AiAnalysis aiAnalysis;

    

}
