package com.example.emotionPlatform.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
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

    // Estrae l'username(email) dal token in modo che si possa validare nelle richieste
    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    // Claims sono tutte le informazioni contenute nel payload del JWT
    // Estrae tutti i dati presenti nel payload del JWT
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                   .verifyWith(getKey())
                   .build()
                   .parseSignedClaims(token) // analisi della firma prende: HEADER.PAYLOAD.SIGNATURE e verifica la firma
                   .getPayload(); // restituisce solo il payload
    }

    // Recupera la data di scadenza del token
    private Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    // Controlla se il token è scaduto

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // Verifica che il tokene appartenga all'utente corretto
    // e che non sia scaduto
    public boolean isTokenValid(String token, UserDetails userDetails){
        String userName = extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
