//package com.sri.spring_ai_workshop.rag;
//
//import org.elasticsearch.client.RestClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.ai.document.Document;
//import org.springframework.ai.embedding.EmbeddingModel;
//import org.springframework.ai.reader.JsonReader;
//import org.springframework.ai.vectorstore.SimpleVectorStore;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.ai.vectorstore.elasticsearch.ElasticsearchVectorStore;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.Resource;
//
//import java.io.File;
//import java.nio.file.Paths;
//import java.util.List;
//
//@Configuration
//public class RagConfiguration {
//
//    private static final Logger log = LoggerFactory.getLogger(RagConfiguration.class);
//
//    @Value("vectorstore.json")
//    private String vectorStoreName;
//
//    @Value("classpath:/data/city-details.json")
//    private Resource cityResource;
//
//    @Bean
//    @Primary
//    public VectorStore vectorStore(EmbeddingModel embeddingModel, RestClient restClient) {
//        // Look here! You are HANDING the Gemini model to the VectorStore.
//        return ElasticsearchVectorStore.builder(restClient, embeddingModel)
//                .build();
//    }
//
//    @Bean
//    SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel) {
//        SimpleVectorStore vectorStore = SimpleVectorStore.builder(embeddingModel).build();
//        File storageFile = getVectorStoreFile();
//
//        if (storageFile.exists()) {
//            log.info("Loading existing Vector Store from: {}", storageFile.getAbsolutePath());
//            vectorStore.load(storageFile);
//        } else {
//            log.info("Vector Store not found. Initializing with JSON data...");
//
//            // 1. Use JsonReader instead of TextReader
//            // "description" is the field we want to turn into a Vector
//            JsonReader jsonReader = new JsonReader(cityResource, "description", "city_name", "population");
//
//            // 2. This automatically creates a List<Document> with metadata attached
//            List<Document> documents = jsonReader.get();
//
//            // 3. Add to store and persist
//            vectorStore.add(documents);
//            vectorStore.save(storageFile);
//        }
//        return vectorStore;
//    }
//
//    private File getVectorStoreFile() {
//        return Paths.get("src/main/resources/data", vectorStoreName).toFile();
//    }
//}