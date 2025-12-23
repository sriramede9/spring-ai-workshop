package com.sri.spring_ai_workshop.multimodal;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ImageGeneration {

    private final ImageModel imageModel;

    public ImageGeneration(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping("/generate-image")
    public ResponseEntity<Map<String, String>> generateImage(
            @RequestParam(defaultValue = "A futuristic library in 2025") String prompt) {

        // We use the portable ImageOptionsBuilder to avoid "symbol not found" errors
        ImageOptions options = ImageOptionsBuilder.builder()
                .model("imagen-3") // Google's image model name
                .N(1)
                .height(1024)
                .width(1024)
                .build();

        ImageResponse response = imageModel.call(new ImagePrompt(prompt, options));

        // Gemini returns the image data here
        String imageUrl = response.getResult().getOutput().getUrl();

        return ResponseEntity.ok(Map.of(
                "prompt", prompt,
                "imageUrl", imageUrl != null ? imageUrl : "Check logs for Base64 data"
        ));
    }
}