package com.event_app.Event_App.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Tüm URL'lere izin ver
                .allowedOrigins("https://localhost:3000") // Frontend URL'si
                .allowedMethods("GET", "POST", "PUT", "DELETE") // İzin verilen HTTP metotları
                .allowedHeaders("*") // Tüm başlıklara izin ver
                .allowCredentials(true); // Kimlik doğrulama bilgilerine izin ver
    }
}


