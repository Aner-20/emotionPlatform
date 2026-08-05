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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
            // Recupera header Authorization
            final String authHeader = request.getHeader("Authorization");
            
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
                    
                    authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    // Salva utente autenticato nel contesto Security
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }
            }

            // Passa alla catena successiva
            filterChain.doFilter(request, response);

    }
    
    
}
