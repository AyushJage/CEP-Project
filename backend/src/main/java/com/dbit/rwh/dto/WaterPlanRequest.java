package com.dbit.rwh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WaterPlanRequest {
    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "Season is required (KHARIF, RABI, ZAID)")
    private String season;

    @NotNull(message = "Catchment area is required")
    @Positive
    private Double catchmentAreaSqm;

    @NotNull(message = "Tank capacity is required")
    @Positive
    private Double tankCapacityLitres;

    @NotBlank(message = "Planned crop is required")
    private String cropName;

    public WaterPlanRequest() {}

    public WaterPlanRequest(String district, String season, Double catchmentAreaSqm, Double tankCapacityLitres, String cropName) {
        this.district = district;
        this.season = season;
        this.catchmentAreaSqm = catchmentAreaSqm;
        this.tankCapacityLitres = tankCapacityLitres;
        this.cropName = cropName;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSeason() {
        return this.season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Double getCatchmentAreaSqm() {
        return this.catchmentAreaSqm;
    }

    public void setCatchmentAreaSqm(Double catchmentAreaSqm) {
        this.catchmentAreaSqm = catchmentAreaSqm;
    }

    public Double getTankCapacityLitres() {
        return this.tankCapacityLitres;
    }

    public void setTankCapacityLitres(Double tankCapacityLitres) {
        this.tankCapacityLitres = tankCapacityLitres;
    }

    public String getCropName() {
        return this.cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

}
