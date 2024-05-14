package com.example.chatgpt.service;

import com.example.chatgpt.config.OpenAIClient;
import com.example.chatgpt.model.ChatRequest;
import com.example.chatgpt.model.ChatResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ChatbotService {

    private final OpenAIClient openAIClient;

    public ChatbotService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    public ChatResponse getResponse(ChatRequest request) {
        String aiResponse = openAIClient.getChatResponse(request.getMessage());
        ChatResponse response = new ChatResponse();

        // Parse the response JSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(aiResponse);
            String content = root.path("choices").get(0).path("message").path("content").asText();
            response.setMessage(content);
        } catch (IOException e) {
            e.printStackTrace();
            response.setMessage("Error processing the response from OpenAI.");
        }

        return response;
    }
}
