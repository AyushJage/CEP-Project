package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "land_data")
public class LandData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @Column(name = "plot_name", nullable = false)
    private String plotName;

    @Column(name = "area_sqm", nullable = false)
    private Double areaSqm;

    @Column(name = "surface_type", nullable = false)
    private String surfaceType; // Roof/concrete (0.85), Compacted soil (0.60), Farmland/cropped (0.40), Grassland (0.30), Forest/vegetated (0.20)

    private String village;
    private String taluka;
    private String district;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public LandData() {}

    public LandData(Long id, User farmer, String plotName, Double areaSqm, String surfaceType, String village, String taluka, String district, String notes, LocalDateTime createdAt) {
        this.id = id;
        this.farmer = farmer;
        this.plotName = plotName;
        this.areaSqm = areaSqm;
        this.surfaceType = surfaceType;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
        this.notes = notes;
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

    public String getPlotName() {
        return this.plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public Double getAreaSqm() {
        return this.areaSqm;
    }

    public void setAreaSqm(Double areaSqm) {
        this.areaSqm = areaSqm;
    }

    public String getSurfaceType() {
        return this.surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = surfaceType;
    }

    public String getVillage() {
        return this.village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTaluka() {
        return this.taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static LandDataBuilder builder() {
        return new LandDataBuilder();
    }

    public static class LandDataBuilder {
        private Long id;
        private User farmer;
        private String plotName;
        private Double areaSqm;
        private String surfaceType;
        private String village;
        private String taluka;
        private String district;
        private String notes;
        private LocalDateTime createdAt;

        public LandDataBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LandDataBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public LandDataBuilder plotName(String plotName) {
            this.plotName = plotName;
            return this;
        }

        public LandDataBuilder areaSqm(Double areaSqm) {
            this.areaSqm = areaSqm;
            return this;
        }

        public LandDataBuilder surfaceType(String surfaceType) {
            this.surfaceType = surfaceType;
            return this;
        }

        public LandDataBuilder village(String village) {
            this.village = village;
            return this;
        }

        public LandDataBuilder taluka(String taluka) {
            this.taluka = taluka;
            return this;
        }

        public LandDataBuilder district(String district) {
            this.district = district;
            return this;
        }

        public LandDataBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public LandDataBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public LandData build() {
            LandData instance = new LandData();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setPlotName(this.plotName);
            instance.setAreaSqm(this.areaSqm);
            instance.setSurfaceType(this.surfaceType);
            instance.setVillage(this.village);
            instance.setTaluka(this.taluka);
            instance.setDistrict(this.district);
            instance.setNotes(this.notes);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
