package com.sri.spring_ai_workshop.service;

import com.sri.spring_ai_workshop.model.PolicyUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KafkaIngestionService {

    private final VectorStore vectorStore;
    private static final Logger logger = LoggerFactory.getLogger(KafkaIngestionService.class);

    public KafkaIngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @KafkaListener(topics = "bank-policy-updates", groupId = "pc-bank-rag-group")
    public void consumePolicyUpdate(PolicyUpdate update) {
        logger.info("Received streaming update for: {}", update.title());

        // 1. Convert POJO to Spring AI Document
        // We put 'category' in metadata to allow for Senior-level filtering later
        Document document = new Document(
                "Title: " + update.title() + "\nContent: " + update.content(),
                Map.of("policy_id", update.id(), "category", update.category())
        );

        // 2. The VectorStore.add() method automatically triggers the Gemini Embedding 
        // and saves the vector to Elasticsearch.
        vectorStore.add(List.of(document));
        
        logger.info("Successfully indexed vector for policy: {}", update.id());
    }
}