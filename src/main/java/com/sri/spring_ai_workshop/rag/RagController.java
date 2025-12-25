package com.sri.spring_ai_workshop.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class RagController {
    private final ChatClient chatClient;

    public  RagController(ChatClient.Builder chatClientBuilder,@Qualifier("vectorStore") VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build()).build();
    }

    @GetMapping("/rag/ask")
    public String getVectorAnswer(@RequestParam String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();

    }

}
