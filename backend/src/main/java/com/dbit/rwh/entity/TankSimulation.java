package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tank_simulation")
public class TankSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(name = "sim_year", nullable = false)
    private Integer year;

    @Column(name = "catchment_area_sqm", nullable = false)
    private Double catchmentAreaSqm;

    @Column(name = "runoff_coeff", nullable = false)
    private Double runoffCoeff;

    @Column(name = "capacity_litres", nullable = false)
    private Double capacityLitres;

    @Column(name = "daily_demand_litres", nullable = false)
    private Double dailyDemandLitres;

    @Column(name = "summary_json", columnDefinition = "LONGTEXT")
    private String summaryJson;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public TankSimulation() {}

    public TankSimulation(Long id, User farmer, String city, Integer year, Double catchmentAreaSqm, Double runoffCoeff, Double capacityLitres, Double dailyDemandLitres, String summaryJson, LocalDateTime createdAt) {
        this.id = id;
        this.farmer = farmer;
        this.city = city;
        this.year = year;
        this.catchmentAreaSqm = catchmentAreaSqm;
        this.runoffCoeff = runoffCoeff;
        this.capacityLitres = capacityLitres;
        this.dailyDemandLitres = dailyDemandLitres;
        this.summaryJson = summaryJson;
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

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getCatchmentAreaSqm() {
        return this.catchmentAreaSqm;
    }

    public void setCatchmentAreaSqm(Double catchmentAreaSqm) {
        this.catchmentAreaSqm = catchmentAreaSqm;
    }

    public Double getRunoffCoeff() {
        return this.runoffCoeff;
    }

    public void setRunoffCoeff(Double runoffCoeff) {
        this.runoffCoeff = runoffCoeff;
    }

    public Double getCapacityLitres() {
        return this.capacityLitres;
    }

    public void setCapacityLitres(Double capacityLitres) {
        this.capacityLitres = capacityLitres;
    }

    public Double getDailyDemandLitres() {
        return this.dailyDemandLitres;
    }

    public void setDailyDemandLitres(Double dailyDemandLitres) {
        this.dailyDemandLitres = dailyDemandLitres;
    }

    public String getSummaryJson() {
        return this.summaryJson;
    }

    public void setSummaryJson(String summaryJson) {
        this.summaryJson = summaryJson;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static TankSimulationBuilder builder() {
        return new TankSimulationBuilder();
    }

    public static class TankSimulationBuilder {
        private Long id;
        private User farmer;
        private String city;
        private Integer year;
        private Double catchmentAreaSqm;
        private Double runoffCoeff;
        private Double capacityLitres;
        private Double dailyDemandLitres;
        private String summaryJson;
        private LocalDateTime createdAt;

        public TankSimulationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TankSimulationBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public TankSimulationBuilder city(String city) {
            this.city = city;
            return this;
        }

        public TankSimulationBuilder year(Integer year) {
            this.year = year;
            return this;
        }

        public TankSimulationBuilder catchmentAreaSqm(Double catchmentAreaSqm) {
            this.catchmentAreaSqm = catchmentAreaSqm;
            return this;
        }

        public TankSimulationBuilder runoffCoeff(Double runoffCoeff) {
            this.runoffCoeff = runoffCoeff;
            return this;
        }

        public TankSimulationBuilder capacityLitres(Double capacityLitres) {
            this.capacityLitres = capacityLitres;
            return this;
        }

        public TankSimulationBuilder dailyDemandLitres(Double dailyDemandLitres) {
            this.dailyDemandLitres = dailyDemandLitres;
            return this;
        }

        public TankSimulationBuilder summaryJson(String summaryJson) {
            this.summaryJson = summaryJson;
            return this;
        }

        public TankSimulationBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TankSimulation build() {
            TankSimulation instance = new TankSimulation();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setCity(this.city);
            instance.setYear(this.year);
            instance.setCatchmentAreaSqm(this.catchmentAreaSqm);
            instance.setRunoffCoeff(this.runoffCoeff);
            instance.setCapacityLitres(this.capacityLitres);
            instance.setDailyDemandLitres(this.dailyDemandLitres);
            instance.setSummaryJson(this.summaryJson);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
