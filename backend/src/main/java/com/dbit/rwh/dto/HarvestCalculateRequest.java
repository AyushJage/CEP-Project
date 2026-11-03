package com.dbit.rwh.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class HarvestCalculateRequest {

    @NotNull(message = "Catchment area is required")
    @Positive(message = "Catchment area must be positive")
    private Double catchmentAreaSqm;

    @NotNull(message = "Rainfall amount is required")
    @Positive(message = "Rainfall must be positive")
    private Double rainfallMm;

    @NotNull(message = "Surface type is required")
    private String surfaceType; // Roof/concrete, Compacted soil, Farmland/cropped, Grassland, Forest/vegetated

    public HarvestCalculateRequest() {}

    public HarvestCalculateRequest(Double catchmentAreaSqm, Double rainfallMm, String surfaceType) {
        this.catchmentAreaSqm = catchmentAreaSqm;
        this.rainfallMm = rainfallMm;
        this.surfaceType = surfaceType;
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

    public String getSurfaceType() {
        return this.surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = surfaceType;
    }

}
