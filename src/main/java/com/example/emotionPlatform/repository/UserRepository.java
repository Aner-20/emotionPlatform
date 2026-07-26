package com.example.emotionPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emotionPlatform.entity.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    // Optional: l'utente potrebbe non esistere
    Optional<User> findByEmail(String email);
}
