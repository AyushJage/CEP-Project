package com.dbit.rwh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SimulationRequest {
    @NotBlank(message = "City is required")
    private String city; // Mumbai, Pune, Nagpur, etc.

    @NotNull(message = "Catchment area is required")
    @Positive
    private Double catchmentAreaSqm;

    @NotNull(message = "Runoff coefficient is required")
    @Positive
    private Double runoffCoeff;

    @NotNull(message = "Capacity in litres is required")
    @Positive
    private Double capacityLitres;

    @NotNull(message = "Daily demand in litres is required")
    @Positive
    private Double dailyDemandLitres;

    private Integer year; // 1990 - 2022

    public SimulationRequest() {}

    public SimulationRequest(String city, Double catchmentAreaSqm, Double runoffCoeff, Double capacityLitres, Double dailyDemandLitres, Integer year) {
        this.city = city;
        this.catchmentAreaSqm = catchmentAreaSqm;
        this.runoffCoeff = runoffCoeff;
        this.capacityLitres = capacityLitres;
        this.dailyDemandLitres = dailyDemandLitres;
        this.year = year;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
