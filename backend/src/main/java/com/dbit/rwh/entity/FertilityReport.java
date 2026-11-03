package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fertility_report")
public class FertilityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    private Double ph;
    private Double nitrogen;       // kg/ha
    private Double phosphorus;     // kg/ha
    private Double potassium;      // kg/ha
    
    @Column(name = "organic_carbon")
    private Double organicCarbon;  // %
    
    private Double ec;             // dS/m

    @Column(nullable = false)
    private Double score;          // 0 - 100

    @Column(nullable = false, length = 20)
    private String band;           // Poor, Moderate, Good, Excellent

    @Column(name = "tips_json", columnDefinition = "TEXT")
    private String tipsJson;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public FertilityReport() {}

    public FertilityReport(Long id, User farmer, Double ph, Double nitrogen, Double phosphorus, Double potassium, Double organicCarbon, Double ec, Double score, String band, String tipsJson, LocalDateTime createdAt) {
        this.id = id;
        this.farmer = farmer;
        this.ph = ph;
        this.nitrogen = nitrogen;
        this.phosphorus = phosphorus;
        this.potassium = potassium;
        this.organicCarbon = organicCarbon;
        this.ec = ec;
        this.score = score;
        this.band = band;
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

    public Double getPh() {
        return this.ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getNitrogen() {
        return this.nitrogen;
    }

    public void setNitrogen(Double nitrogen) {
        this.nitrogen = nitrogen;
    }

    public Double getPhosphorus() {
        return this.phosphorus;
    }

    public void setPhosphorus(Double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Double getPotassium() {
        return this.potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getOrganicCarbon() {
        return this.organicCarbon;
    }

    public void setOrganicCarbon(Double organicCarbon) {
        this.organicCarbon = organicCarbon;
    }

    public Double getEc() {
        return this.ec;
    }

    public void setEc(Double ec) {
        this.ec = ec;
    }

    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getBand() {
        return this.band;
    }

    public void setBand(String band) {
        this.band = band;
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

    public static FertilityReportBuilder builder() {
        return new FertilityReportBuilder();
    }

    public static class FertilityReportBuilder {
        private Long id;
        private User farmer;
        private Double ph;
        private Double nitrogen;
        private Double phosphorus;
        private Double potassium;
        private Double organicCarbon;
        private Double ec;
        private Double score;
        private String band;
        private String tipsJson;
        private LocalDateTime createdAt;

        public FertilityReportBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public FertilityReportBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public FertilityReportBuilder ph(Double ph) {
            this.ph = ph;
            return this;
        }

        public FertilityReportBuilder nitrogen(Double nitrogen) {
            this.nitrogen = nitrogen;
            return this;
        }

        public FertilityReportBuilder phosphorus(Double phosphorus) {
            this.phosphorus = phosphorus;
            return this;
        }

        public FertilityReportBuilder potassium(Double potassium) {
            this.potassium = potassium;
            return this;
        }

        public FertilityReportBuilder organicCarbon(Double organicCarbon) {
            this.organicCarbon = organicCarbon;
            return this;
        }

        public FertilityReportBuilder ec(Double ec) {
            this.ec = ec;
            return this;
        }

        public FertilityReportBuilder score(Double score) {
            this.score = score;
            return this;
        }

        public FertilityReportBuilder band(String band) {
            this.band = band;
            return this;
        }

        public FertilityReportBuilder tipsJson(String tipsJson) {
            this.tipsJson = tipsJson;
            return this;
        }

        public FertilityReportBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FertilityReport build() {
            FertilityReport instance = new FertilityReport();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setPh(this.ph);
            instance.setNitrogen(this.nitrogen);
            instance.setPhosphorus(this.phosphorus);
            instance.setPotassium(this.potassium);
            instance.setOrganicCarbon(this.organicCarbon);
            instance.setEc(this.ec);
            instance.setScore(this.score);
            instance.setBand(this.band);
            instance.setTipsJson(this.tipsJson);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
