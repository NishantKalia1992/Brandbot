package com.brandbot.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AIgenerationService {

    // In a real role, this would be the Adobe Firefly API endpoint
    private final String AI_API_URL = "https://api.openai.com/v1/images/generations"; 
    private final String API_KEY = "YOUR_API_KEY_HERE";

    private final WebClient webClient;

    public AIgenerationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(AI_API_URL).build();
    }

    public String generateImage(String prompt) {
        // 1. Construct the Payload (The "Prompt Engineering" part)
        String requestBody = """
            {
                "prompt": "%s",
                "n": 1,
                "size": "1024x1024"
            }
            """.formatted(prompt);

        // 2. Call the AI API
        // This is where you demonstrate "Cloud Savvy" - handling async API calls
        System.out.println("🤖 Asking AI to generate: " + prompt);
        
        // MOCK RESPONSE for now (so you can run this without paying $)
        // In a real interview, you'd show the WebClient call here.
        return "https://picsum.photos/seed/" + prompt.hashCode() + "/500/500";
    }
    
    public String imageCreate(String prompt) {
    	System.out.println("🤖 Asking backup AI (LoremFlickr) for: " + prompt);

        // 1. Clean the prompt: Extract the last word as a keyword (e.g., "futuristic car" -> "car")
        // This is a simple hack because LoremFlickr prefers single keywords.
        String[] words = prompt.split(" ");
        String keyword = words[words.length - 1]; 

        // 2. Construct a URL that works
        // Pattern: https://loremflickr.com/{width}/{height}/{keyword}
        String finalUrl = "https://loremflickr.com/800/600/" + keyword;

        System.out.println("✨ Generated Backup Link: " + finalUrl);
        
        // 3. Return the URL
        return finalUrl;
    }
}