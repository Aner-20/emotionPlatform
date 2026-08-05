package com.example.emotionPlatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // dice a spring che questa classe contiene configurazioni da caricare all'avvio dell'applicazione
@EnableMethodSecurity
public class SecurityConfig {

    // HttpSecurity http: oggetto con cui si configura Spring security
    // Csrf: Cross-Site Request Forgery, protezione contro richieste malevole provenienti da un altro sito

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

            // Disabilita CSRF per le API REST
            .csrf(csrf -> csrf.disable())

            // Non si usano sessioni


            // Autorizzazioni
            .authorizeHttpRequests(auth -> auth

                // Swagger
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()

                // Tutte le API (solo durante lo sviluppo)
                .anyRequest().permitAll()
            )

            // Login HTTP Basic (non verrà usato perché tutto è permitAll)
            // Il browser mostra una finestra con Username: e password: 
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    

}
