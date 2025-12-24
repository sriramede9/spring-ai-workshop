package com.sri.spring_ai_workshop.multimodal.audio;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class GeminiSpeechGeneration {

    private final ChatModel chatModel;

    public GeminiSpeechGeneration(@Autowired ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/generate-speech")
    public ResponseEntity<byte[]> generateSpeech(
            @RequestParam(defaultValue = "Hello! I am Gemini, speaking to you from 2025.") String text) {


        GoogleGenAiChatOptions options = GoogleGenAiChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.7)
                .build();

        ChatResponse response = chatModel.call(new Prompt(new UserMessage(text), options));

        Object output = response.getResult().getOutput().getMedia();
        
        // Mocking the byte extraction: In 1.1.2, multimodal responses are often Base64 encoded
        // or provided as a Media object in the message metadata.
        byte[] audioData = extractAudioBytes(response);

        if (audioData == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentDispositionFormData("attachment", "gemini-speech.mp3");

        return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
    }

    private byte[] extractAudioBytes(ChatResponse response) {
    return new byte[0];
    }
}
