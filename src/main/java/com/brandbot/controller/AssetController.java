package com.brandbot.controller;


import org.springframework.web.bind.annotation.*;

import com.brandbot.services.AIgenerationService;
import com.brandbot.services.AdobeIntegerationService;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin(origins = "http://localhost:3000") // Allow React Frontend
public class AssetController {

    private final AIgenerationService aiService;
    private final AdobeIntegerationService adobeService;

    public AssetController(AIgenerationService aiService, AdobeIntegerationService adobeService) {
        this.aiService = aiService;
        this.adobeService = adobeService;
    }

    @PostMapping("/create")
    public String createAsset(@RequestBody AssetRequest request) {
        // Step 1: AI Generation
        String imageUrl = aiService.generateImage(request.prompt());
        
        // Step 2: Adobe Integration
        adobeService.uploadToAEM(imageUrl, "summer-campaign-2025");
        
        return "Process Complete! Image created and sent to Adobe: " + imageUrl;
    }
    
    @PostMapping("/generate")
    public String generateAsset(@RequestBody AssetRequest request) {
    	String image = aiService.imageCreate(request.prompt());
    	return "image generated successfully and you can check"+image;
    } 
}

// Simple Record for the input
record AssetRequest(String prompt) {}