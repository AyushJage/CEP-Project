package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "learning_guide")
public class LearningGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_en", nullable = false)
    private String titleEn;

    @Column(name = "title_mr", nullable = false)
    private String titleMr;

    @Column(name = "body_en", columnDefinition = "LONGTEXT", nullable = false)
    private String bodyEn;

    @Column(name = "body_mr", columnDefinition = "LONGTEXT", nullable = false)
    private String bodyMr;

    @Column(nullable = false, length = 50)
    private String category; // STRUCTURE, MAINTENANCE, IRRIGATION, SOIL, COMMUNITY

    @Column(name = "target_situation")
    private String targetSituation; // e.g., "no structure + rain-fed + small land"

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public LearningGuide() {}

    public LearningGuide(Long id, String titleEn, String titleMr, String bodyEn, String bodyMr, String category, String targetSituation, String imageUrl, LocalDateTime createdAt) {
        this.id = id;
        this.titleEn = titleEn;
        this.titleMr = titleMr;
        this.bodyEn = bodyEn;
        this.bodyMr = bodyMr;
        this.category = category;
        this.targetSituation = targetSituation;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleEn() {
        return this.titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleMr() {
        return this.titleMr;
    }

    public void setTitleMr(String titleMr) {
        this.titleMr = titleMr;
    }

    public String getBodyEn() {
        return this.bodyEn;
    }

    public void setBodyEn(String bodyEn) {
        this.bodyEn = bodyEn;
    }

    public String getBodyMr() {
        return this.bodyMr;
    }

    public void setBodyMr(String bodyMr) {
        this.bodyMr = bodyMr;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTargetSituation() {
        return this.targetSituation;
    }

    public void setTargetSituation(String targetSituation) {
        this.targetSituation = targetSituation;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static LearningGuideBuilder builder() {
        return new LearningGuideBuilder();
    }

    public static class LearningGuideBuilder {
        private Long id;
        private String titleEn;
        private String titleMr;
        private String bodyEn;
        private String bodyMr;
        private String category;
        private String targetSituation;
        private String imageUrl;
        private LocalDateTime createdAt;

        public LearningGuideBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LearningGuideBuilder titleEn(String titleEn) {
            this.titleEn = titleEn;
            return this;
        }

        public LearningGuideBuilder titleMr(String titleMr) {
            this.titleMr = titleMr;
            return this;
        }

        public LearningGuideBuilder bodyEn(String bodyEn) {
            this.bodyEn = bodyEn;
            return this;
        }

        public LearningGuideBuilder bodyMr(String bodyMr) {
            this.bodyMr = bodyMr;
            return this;
        }

        public LearningGuideBuilder category(String category) {
            this.category = category;
            return this;
        }

        public LearningGuideBuilder targetSituation(String targetSituation) {
            this.targetSituation = targetSituation;
            return this;
        }

        public LearningGuideBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public LearningGuideBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public LearningGuide build() {
            LearningGuide instance = new LearningGuide();
            instance.setId(this.id);
            instance.setTitleEn(this.titleEn);
            instance.setTitleMr(this.titleMr);
            instance.setBodyEn(this.bodyEn);
            instance.setBodyMr(this.bodyMr);
            instance.setCategory(this.category);
            instance.setTargetSituation(this.targetSituation);
            instance.setImageUrl(this.imageUrl);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
