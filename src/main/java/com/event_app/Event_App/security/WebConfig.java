package com.event_app.Event_App.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Tüm URL'ler için CORS
                .allowedOrigins("http://localhost:3000")  // Frontend'in çalıştığı port
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Hangi HTTP metodlarına izin verileceği
                .allowedHeaders("*");  // Tüm başlıklara izin ver
    }

}

