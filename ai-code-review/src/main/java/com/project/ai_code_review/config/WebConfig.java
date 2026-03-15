package com.project.ai_code_review.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // FIX: list the actual frontend origin instead of wildcard "*"
                // when you deploy, replace with your real domain
                .allowedOrigins(
                        "http://localhost:3000",   // React dev server default
                        "http://localhost:3001",   // in case 3000 is taken
                        "http://127.0.0.1:3000"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}