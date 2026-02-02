package com.project.ai_code_review.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

public class AiService {

    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GEMINI_URL="https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent" ;//generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent" ;
    public String analyzeCode(String code) {
        if(apiKey == null|| apiKey.isEmpty()) {
            return "Error: Google API Key is missing. PLease set the api key in application properties.";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", apiKey);

        String promptText = "You are a senior software engineer. Analyze the following code and provide constructive feedback, suggestions for improvement, and highlight any potential issues:\n\n" + code;
        return null;
    }
}