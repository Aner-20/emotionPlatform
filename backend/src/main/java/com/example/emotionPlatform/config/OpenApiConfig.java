package com.example.emotionPlatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

// Per personalizzare Swagger
// @EnableSpringDataWebSupport abilita/configura le funzionalità di Spring Data per il web
// Quindi: Pageable, page, parametri page, size, sort, serializzazione delle pagine
// VIA_DTO: non serializza direttamente PageImpl, ma usa una rappresentazione DTO stabile per la pagina
@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class OpenApiConfig {
    
    @Bean
    public OpenAPI emotionPlatformOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Emotion Platform API")
                        .description("REST API for notes handling and emotions analysis through AI.")
                        .version("1.0.0")

                    
                    )

                    // Componenti: sezione di OpenApi dove si mettono elemnenti riutilizzabili
                    // Configurazione JWT per Swagger
                    .components(
                        new Components()
                            .addSecuritySchemes(
                                "bearerAuth", // nome dello schema
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP) // tipo di autenticazione
                                        .scheme("bearer") // formato standard JWT 
                                        .bearerFormat("JWT")
                            )
                    )  

                    // Applica il token alle richieste
                    .addSecurityItem(
                        new SecurityRequirement()
                            .addList("bearerAuth") // usa questo schema di sicureza per le chiamate Swagger
                    );

                }
}
