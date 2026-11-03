package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_prediction")
public class AiPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id")
    private User farmer;

    @Column(nullable = false, length = 50)
    private String type; // RAINFALL, FERTILITY, CROP, WATER_PLAN, STRUCTURE

    @Column(name = "input_json", columnDefinition = "TEXT")
    private String inputJson;

    @Column(name = "output_json", columnDefinition = "TEXT")
    private String outputJson;

    private Double confidence;

    @Column(name = "model_version")
    private String modelVersion;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public AiPrediction() {}

    public AiPrediction(Long id, User farmer, String type, String inputJson, String outputJson, Double confidence, String modelVersion, LocalDateTime createdAt) {
        this.id = id;
        this.farmer = farmer;
        this.type = type;
        this.inputJson = inputJson;
        this.outputJson = outputJson;
        this.confidence = confidence;
        this.modelVersion = modelVersion;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFarmer() {
        return this.farmer;
    }

    public void setFarmer(User farmer) {
        this.farmer = farmer;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInputJson() {
        return this.inputJson;
    }

    public void setInputJson(String inputJson) {
        this.inputJson = inputJson;
    }

    public String getOutputJson() {
        return this.outputJson;
    }

    public void setOutputJson(String outputJson) {
        this.outputJson = outputJson;
    }

    public Double getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getModelVersion() {
        return this.modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static AiPredictionBuilder builder() {
        return new AiPredictionBuilder();
    }

    public static class AiPredictionBuilder {
        private Long id;
        private User farmer;
        private String type;
        private String inputJson;
        private String outputJson;
        private Double confidence;
        private String modelVersion;
        private LocalDateTime createdAt;

        public AiPredictionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AiPredictionBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public AiPredictionBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AiPredictionBuilder inputJson(String inputJson) {
            this.inputJson = inputJson;
            return this;
        }

        public AiPredictionBuilder outputJson(String outputJson) {
            this.outputJson = outputJson;
            return this;
        }

        public AiPredictionBuilder confidence(Double confidence) {
            this.confidence = confidence;
            return this;
        }

        public AiPredictionBuilder modelVersion(String modelVersion) {
            this.modelVersion = modelVersion;
            return this;
        }

        public AiPredictionBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AiPrediction build() {
            AiPrediction instance = new AiPrediction();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setType(this.type);
            instance.setInputJson(this.inputJson);
            instance.setOutputJson(this.outputJson);
            instance.setConfidence(this.confidence);
            instance.setModelVersion(this.modelVersion);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
