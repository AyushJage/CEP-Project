package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendation")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @Column(name = "structure_type", nullable = false)
    private String structureType; // Farm pond (Shet-tale), Masonry storage tank, Rooftop harvesting, Check dam, Percolation pit, Contour bunding

    @Column(name = "suggested_capacity_litres")
    private Double suggestedCapacityLitres;

    @Column(name = "reason_summary", columnDefinition = "TEXT")
    private String reasonSummary;

    @Column(name = "tips_json", columnDefinition = "TEXT")
    private String tipsJson;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public Recommendation() {}

    public Recommendation(Long id, User farmer, String structureType, Double suggestedCapacityLitres, String reasonSummary, String tipsJson, LocalDateTime createdAt) {
        this.id = id;
        this.farmer = farmer;
        this.structureType = structureType;
        this.suggestedCapacityLitres = suggestedCapacityLitres;
        this.reasonSummary = reasonSummary;
        this.tipsJson = tipsJson;
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

    public String getStructureType() {
        return this.structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public Double getSuggestedCapacityLitres() {
        return this.suggestedCapacityLitres;
    }

    public void setSuggestedCapacityLitres(Double suggestedCapacityLitres) {
        this.suggestedCapacityLitres = suggestedCapacityLitres;
    }

    public String getReasonSummary() {
        return this.reasonSummary;
    }

    public void setReasonSummary(String reasonSummary) {
        this.reasonSummary = reasonSummary;
    }

    public String getTipsJson() {
        return this.tipsJson;
    }

    public void setTipsJson(String tipsJson) {
        this.tipsJson = tipsJson;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static RecommendationBuilder builder() {
        return new RecommendationBuilder();
    }

    public static class RecommendationBuilder {
        private Long id;
        private User farmer;
        private String structureType;
        private Double suggestedCapacityLitres;
        private String reasonSummary;
        private String tipsJson;
        private LocalDateTime createdAt;

        public RecommendationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RecommendationBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public RecommendationBuilder structureType(String structureType) {
            this.structureType = structureType;
            return this;
        }

        public RecommendationBuilder suggestedCapacityLitres(Double suggestedCapacityLitres) {
            this.suggestedCapacityLitres = suggestedCapacityLitres;
            return this;
        }

        public RecommendationBuilder reasonSummary(String reasonSummary) {
            this.reasonSummary = reasonSummary;
            return this;
        }

        public RecommendationBuilder tipsJson(String tipsJson) {
            this.tipsJson = tipsJson;
            return this;
        }

        public RecommendationBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Recommendation build() {
            Recommendation instance = new Recommendation();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setStructureType(this.structureType);
            instance.setSuggestedCapacityLitres(this.suggestedCapacityLitres);
            instance.setReasonSummary(this.reasonSummary);
            instance.setTipsJson(this.tipsJson);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
