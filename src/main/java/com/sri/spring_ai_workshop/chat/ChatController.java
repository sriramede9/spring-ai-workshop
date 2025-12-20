package com.sri.spring_ai_workshop.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {


    private ChatClient chatClient;

    @Autowired
    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/{prompt}")
    public ResponseEntity<String> chat(String prompt) {
        String content = chatClient.prompt(prompt).call().content();
        return org.springframework.http.ResponseEntity.ok(content);
    }

}
