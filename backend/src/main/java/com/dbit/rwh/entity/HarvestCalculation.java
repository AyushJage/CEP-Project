package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "harvest_calculation")
public class HarvestCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @Column(name = "catchment_area_sqm", nullable = false)
    private Double catchmentAreaSqm;

    @Column(name = "rainfall_mm", nullable = false)
    private Double rainfallMm;

    @Column(name = "runoff_coefficient", nullable = false)
    private Double runoffCoefficient;

    @Column(name = "surface_type", nullable = false)
    private String surfaceType;

    @Column(name = "harvested_litres", nullable = false)
    private Double harvestedLitres;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public HarvestCalculation() {}

    public HarvestCalculation(Long id, User farmer, Double catchmentAreaSqm, Double rainfallMm, Double runoffCoefficient, String surfaceType, Double harvestedLitres, LocalDateTime createdAt) {
        this.id = id;
        this.farmer = farmer;
        this.catchmentAreaSqm = catchmentAreaSqm;
        this.rainfallMm = rainfallMm;
        this.runoffCoefficient = runoffCoefficient;
        this.surfaceType = surfaceType;
        this.harvestedLitres = harvestedLitres;
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

    public Double getCatchmentAreaSqm() {
        return this.catchmentAreaSqm;
    }

    public void setCatchmentAreaSqm(Double catchmentAreaSqm) {
        this.catchmentAreaSqm = catchmentAreaSqm;
    }

    public Double getRainfallMm() {
        return this.rainfallMm;
    }

    public void setRainfallMm(Double rainfallMm) {
        this.rainfallMm = rainfallMm;
    }

    public Double getRunoffCoefficient() {
        return this.runoffCoefficient;
    }

    public void setRunoffCoefficient(Double runoffCoefficient) {
        this.runoffCoefficient = runoffCoefficient;
    }

    public String getSurfaceType() {
        return this.surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = surfaceType;
    }

    public Double getHarvestedLitres() {
        return this.harvestedLitres;
    }

    public void setHarvestedLitres(Double harvestedLitres) {
        this.harvestedLitres = harvestedLitres;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static HarvestCalculationBuilder builder() {
        return new HarvestCalculationBuilder();
    }

    public static class HarvestCalculationBuilder {
        private Long id;
        private User farmer;
        private Double catchmentAreaSqm;
        private Double rainfallMm;
        private Double runoffCoefficient;
        private String surfaceType;
        private Double harvestedLitres;
        private LocalDateTime createdAt;

        public HarvestCalculationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public HarvestCalculationBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public HarvestCalculationBuilder catchmentAreaSqm(Double catchmentAreaSqm) {
            this.catchmentAreaSqm = catchmentAreaSqm;
            return this;
        }

        public HarvestCalculationBuilder rainfallMm(Double rainfallMm) {
            this.rainfallMm = rainfallMm;
            return this;
        }

        public HarvestCalculationBuilder runoffCoefficient(Double runoffCoefficient) {
            this.runoffCoefficient = runoffCoefficient;
            return this;
        }

        public HarvestCalculationBuilder surfaceType(String surfaceType) {
            this.surfaceType = surfaceType;
            return this;
        }

        public HarvestCalculationBuilder harvestedLitres(Double harvestedLitres) {
            this.harvestedLitres = harvestedLitres;
            return this;
        }

        public HarvestCalculationBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public HarvestCalculation build() {
            HarvestCalculation instance = new HarvestCalculation();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setCatchmentAreaSqm(this.catchmentAreaSqm);
            instance.setRainfallMm(this.rainfallMm);
            instance.setRunoffCoefficient(this.runoffCoefficient);
            instance.setSurfaceType(this.surfaceType);
            instance.setHarvestedLitres(this.harvestedLitres);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
