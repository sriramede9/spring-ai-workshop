package com.sri.spring_ai_workshop.multimodal.audio;

import org.springframework.ai.openai.audio.speech.SpeechModel; // Standard Spring AI interface
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeechGeneration {

    private final SpeechModel speechModel;

    // The SpeechModel interface is the standard way to interact with TTS in Spring AI
    public SpeechGeneration(@Autowired(required = false) SpeechModel speechModel) {
        this.speechModel = speechModel;
    }

    @GetMapping("/generate-speech")
    public ResponseEntity<byte[]> generateSpeech(
            @RequestParam(defaultValue = "Hello! Welcome to the 2025 Spring AI workshop.") String text) {

        if (speechModel == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }

        // We use the portable SpeechPrompt. Specific models like gemini-2.5-flash-tts 
        // can be configured via application properties or runtime options.
        SpeechResponse response = speechModel.call(new SpeechPrompt(text));

        // The output is typically returned as a byte array (audio data)
        byte[] audioData = response.getResult().getOutput();

        // Set headers to play the audio directly in the browser
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentDispositionFormData("attachment", "speech.mp3");

        return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
    }
}
