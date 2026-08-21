package com.example.emotionPlatform.security;
/*
Ogni richiesta passa da qui
Il filtro: 
1. legge l'header Authorization 
2. estrae il token JWT 
3. prende l'email dal token
4. cerca l'utente nel db
5. verifica il token
6. dice a spring security che l'utente è autenticato

*/

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component // crea in automatico JwtAuthenticationFilters
@RequiredArgsConstructor
// OncePerRequestFilter esegue il filtro una sola volta per ogni richiesta HTTP
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    
    // Questo metodo viene chiamato automaticamente per ogni richiesta
    // Si usa Servlet perchè Spring Boot Web gira su di esso
    // Servlet riceve richieste HTTP e produce risposte HTTP
    // FilterChain è la catena di filtri che una richiesta HTTP deve attraversare prima di arrivare al controller
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
            // Recupera header Authorization
            final String authHeader = request.getHeader("Authorization"); // che restituisce ad esempio: Bearer eyJhbGciOiJIUzI1NiJ9.abc123.xyz(token) 
            
            // Se manca il token continua normalmente
            if (authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }

            // Rimuove "Bearer "
            String jwt = authHeader.substring(7);


            // Estrae email dal token
            String email = jwtService.extractUsername(jwt);

            // Controlla se l'utente non è già autenticato
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){

                // Recupera utente dal database
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                
                // Verifica token
                if(jwtService.isTokenValid(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                        );
                    
                    // aggiunge dettagli come ip, sessione, dettagli http, non fondamentale per JWT, ma è uno standard
                    authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    // Salva utente autenticato nel contesto Security
                    // considera l'utente da questo momento autenticato
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }
            }

            // Passa alla catena successiva e la richiesta va avanti
            filterChain.doFilter(request, response);

    }
    
    
}
