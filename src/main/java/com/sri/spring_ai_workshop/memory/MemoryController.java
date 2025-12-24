package com.sri.spring_ai_workshop.memory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoryController {
    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    public MemoryController(ChatClient.Builder chatClient,ChatMemory chatMemory) {
        this.chatClient = chatClient.build();
        this.chatMemory = chatMemory;

    }

    @GetMapping("/conversation/memory")
    public String conversation(@RequestParam String message){
        MessageWindowChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();
        return chatClient.prompt()
                .user(u -> u.text(message))
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .call()
                .content();
    }
}
