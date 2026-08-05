package com.example.emotionPlatform.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Jwts; // crea il token
import io.jsonwebtoken.security.Keys; // lo firma con una chiave segreta

// Crea i jwt(Json web token)
// Il token jwt permette a un utente di dimostrare la propria identità nelle richieste successive al login senza dover reinserire email e password ogni volta
// Il frontend salva il token in memoria o storage
// Spring security con il token:ù
// 1. prende il token
// 2. verifica la firma
// 3. controlla la scadenza
// 4. legge chi è l'utente
// Il jwt non contiene la password

// Jwt hanno tre parti: HEADER.PAYLOAD.SIGNATURE
// la firma viene creata usando PAYLOAD + SECRET_KEY
// Serve perchè quando il client manda Authorization: Bearer eyJhbGc...
// il backend deve verificare e il token è stato effettivamente creato dal client
@Service
public class JwtService {
    // Da spostare in application.properties
    private static final String SECRET_KEY = "mySuperSecretKeyForEmotionPlatformJwtAuthentication123456";

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                   .subject(userDetails.getUsername())
                   .issuedAt(new Date())
                   .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)
                   )
                   .signWith(getKey())
                   .compact(); // trasforma tutto in una stringa JWT
        }
    
    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(
            SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

}
