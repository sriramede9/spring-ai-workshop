package com.sri.spring_ai_workshop.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {


    private ChatClient chatClient;

    @Autowired
    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/chat/{prompt}")
    public ResponseEntity<String> chat(@PathVariable String prompt) {
        String content = chatClient.prompt().user(prompt).call().content();
        return org.springframework.http.ResponseEntity.ok(content);
    }

    @GetMapping("/stream/{prompt}")
    public ResponseEntity<Flux<String>> stream(@PathVariable String prompt) {
        Flux<String> content = chatClient.prompt().user(prompt)
                .stream().content();
        return org.springframework.http.ResponseEntity.ok(content);
    }
}
