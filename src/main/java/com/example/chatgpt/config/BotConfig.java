package com.example.chatgpt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BotConfig {

    @Value("${OPENAI_API_KEY}")
    private String openaiApiKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OpenAIClient openAIClient(RestTemplate restTemplate) {
        return new OpenAIClient(openaiApiKey, restTemplate);
    }

}
