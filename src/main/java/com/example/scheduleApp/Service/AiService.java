package com.example.scheduleApp.Service;

import org.springframework.stereotype.Service;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

@Service
public class AiService {

    private final ChatModel model;

    public AiService() {

        this.model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("qwen2.5:3b")
                .build();
    }

    public String chat(String message) {

        return model.chat(message);
    }
}