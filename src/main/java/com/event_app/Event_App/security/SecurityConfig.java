/*
package com.event_app.Event_App.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public HttpSecurity httpSecurity(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .authorizeRequests()
                //.antMatchers("/users").permitAll()  // /users endpoint'ine izin ver
                .anyRequest().authenticated();  // Diğer tüm isteklere kimlik doğrulaması gerektir

        return http;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")  // Frontend adresi
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // İzin verilen HTTP metodları
                        .allowedHeaders("*");  // Tüm başlıklara izin veriyoruz
            }
        };
    }
}
*/
