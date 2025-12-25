package com.sri.spring_ai_workshop.rag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BankDataLoader implements CommandLineRunner {

    private final VectorStore vectorStore;
    private final ObjectMapper objectMapper;

    @Value("classpath:/data/bank-branches.json")
    private Resource bankJson;

    public BankDataLoader(VectorStore vectorStore, ObjectMapper objectMapper) {
        this.vectorStore = vectorStore;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Read the JSON file
        List<Map<String, Object>> branches = objectMapper.readValue(
            bankJson.getInputStream(), 
            new TypeReference<>() {}
        );

        // 2. Convert raw JSON into Spring AI "Documents"
        List<Document> documents = branches.stream()
            .map(b -> {
                String content = String.format("Branch: %s. Address: %s. Services: %s.", 
                                 b.get("name"), b.get("address"), b.get("services"));
                
                // Keep the hours and city as "Metadata" (used for filtering later)
                Map<String, Object> metadata = Map.of(
                    "hours", b.get("hours"),
                    "city", b.get("city")
                );
                
                return new Document(content, metadata);
            })
            .toList();

        // 3. Push to Elasticsearch (This triggers Gemini Embeddings automatically)
        System.out.println(">>> Sending bank data to Gemini for embedding...");
        vectorStore.add(documents);
        System.out.println(">>> Bank data successfully stored in Elasticsearch!");
    }
}