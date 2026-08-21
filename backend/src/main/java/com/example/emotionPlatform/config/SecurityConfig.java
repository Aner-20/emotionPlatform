package com.example.emotionPlatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.emotionPlatform.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration // dice a spring che questa classe contiene configurazioni da caricare all'avvio dell'applicazione
@EnableMethodSecurity // fondamentale altrimenti preAuthorize nei controller non funziona
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // HttpSecurity http: oggetto con cui si configura Spring security
    // Csrf: Cross-Site Request Forgery, protezione contro richieste malevole provenienti da un altro sito

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

            // Disabilita CSRF per le API REST
            .csrf(csrf -> csrf.disable())

            // JWT è stateless
            // Spring non deve creare sessioni lato server
            .sessionManagement(session -> 
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )


            // Autorizzazioni
            .authorizeHttpRequests(auth -> auth

                // Swagger
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()


                // Login e registrazione pubblici
                .requestMatchers(
                    "/api/auth/**"
                ).permitAll()

                // Tutte le altre richieste richiedono autenticazione JWT
                .anyRequest().authenticated()

                
                

                // Tutte le API (solo durante lo sviluppo)
                //.anyRequest().permitAll()
            )
                // Filtro JWT prima del filtro standard
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            // Login HTTP Basic (non verrà usato perché tutto è permitAll)
            // Il browser mostra una finestra con Username: e password: 
            // httpBasic non serve più. Se usato adesso(06/08/2026, 18:11) genera errore 
            //.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    

}
