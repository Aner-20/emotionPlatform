package com.example.emotionPlatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

// Per personalizzare Swagger
@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI emotionPlatformOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Emotion Platform API")
                        .description("REST API for notes handling and emotions analysis through AI.")
                        .version("1.0.0")

                    
                    );
                }
}
