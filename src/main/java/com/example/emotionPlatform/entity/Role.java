package com.example.emotionPlatform.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // hibernate salva id 1, name ADMIN, id 2, name USER 
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
