package com.example.emotionPlatform.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "emotions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String color;

    // In modo che noteEmotions non sia null e che possa generare NullPointerException
    @Builder.Default
    @OneToMany(mappedBy = "emotion", orphanRemoval = true)
    private List<NoteEmotion> noteEmotions = new ArrayList<>();

}
