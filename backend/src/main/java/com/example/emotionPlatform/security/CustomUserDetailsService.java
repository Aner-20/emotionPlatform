package com.example.emotionPlatform.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.emotionPlatform.entity.User;
import com.example.emotionPlatform.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// Suo compito: quando un utente fa login con una email, Spring chiede a questa classe di recuperare l'utente dal database

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) 
            throws UsernameNotFoundException {


        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"
                        )
                );


        return user;
    }
        
}
