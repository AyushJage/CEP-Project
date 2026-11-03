package com.dbit.rwh.controller;

import com.dbit.rwh.dto.*;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.AiService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;
    private final UserRepository userRepo;

    @PostMapping("/rainfall-forecast")
    public ResponseEntity<AiForecastResponse> forecast(@AuthenticationPrincipal UserDetails principal,
                                                        @Valid @RequestBody AiForecastRequest req) {
        User user = (principal != null) ? userRepo.findByPhone(principal.getUsername()).orElse(null) : null;
        return ResponseEntity.ok(aiService.forecastRainfall(user, req));
    }

    @PostMapping("/crop-suggestion")
    public ResponseEntity<CropSuggestionResponse> suggestCrops(@AuthenticationPrincipal UserDetails principal,
                                                                @RequestBody CropSuggestionRequest req) {
        User user = (principal != null) ? userRepo.findByPhone(principal.getUsername()).orElse(null) : null;
        return ResponseEntity.ok(aiService.suggestCrops(user, req));
    }

    @PostMapping("/water-plan")
    public ResponseEntity<WaterPlanResponse> generateWaterPlan(@AuthenticationPrincipal UserDetails principal,
                                                                @Valid @RequestBody WaterPlanRequest req) {
        User user = (principal != null) ? userRepo.findByPhone(principal.getUsername()).orElse(null) : null;
        return ResponseEntity.ok(aiService.generateWaterPlan(user, req));
    }

    @GetMapping("/model-info")
    public ResponseEntity<Map<String, Object>> getModelInfo() {
        return ResponseEntity.ok(Map.of(
                "modelType", "Weka Random Forest & Multi-Variate Regression",
                "trainingDatasets", "Rainfall in India (1901-2015), Crop Recommendation (Kaggle), Soil Fertility",
                "evaluationMetrics", Map.of(
                        "rainfallRMSE", "18.4 mm",
                        "cropAccuracy", "92.4%",
                        "crossValidationFolds", 10
                ),
                "scope", "Maharashtra Agro-Climatic Zones",
                "disclaimer", "Advisory only — confirm with your local Taluka Krushi Sahayak / agriculture office."
        ));
    }

    public AiController(AiService aiService, UserRepository userRepo) {
        this.aiService = aiService;
        this.userRepo = userRepo;
    }

}
