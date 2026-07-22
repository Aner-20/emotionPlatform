package com.example.emotionPlatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String password;

    // fetch = FetchType.LAZY indica quando caricare il Role dal database, ovvero solo quando quando serve
    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn si chiede quale colonna ha la foreign key, in questo caso role_id
    // nullable = false: ogni User deve avere un Role obbligatoriamente
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;


    @Column(name = "created_at")
    private LocalDateTime createdAt;


    
}
