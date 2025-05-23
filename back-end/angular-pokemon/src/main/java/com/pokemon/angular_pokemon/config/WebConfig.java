package com.pokemon.angular_pokemon.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

// Essa classe habilita a comunicação do Angular com a API.
@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @SuppressWarnings("null")
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/treinadores/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "DELETE", "PATCH")
                        .allowedHeaders("*");
            }
        };
    }

}
