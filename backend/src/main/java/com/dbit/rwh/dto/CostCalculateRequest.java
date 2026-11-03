package com.dbit.rwh.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class CostCalculateRequest {

    @NotNull(message = "Annual harvested litres is required")
    @PositiveOrZero
    private Double annualHarvestedLitres;

    @NotNull(message = "Tanker price per 1000 litres is required")
    @PositiveOrZero
    private Double tankerPricePer1000L; // e.g. ₹500 - ₹1200 in Maharashtra

    @PositiveOrZero
    private Double electricityOrDieselPerHour; // e.g. ₹60/hr

    @PositiveOrZero
    private Double pumpingHoursSavedPerYear;

    @NotNull(message = "Structure capital implementation cost is required")
    @PositiveOrZero
    private Double structureCost; // e.g. ₹15,000 for farm pond lining / Jalkund

    public CostCalculateRequest() {}

    public CostCalculateRequest(Double annualHarvestedLitres, Double tankerPricePer1000L, Double electricityOrDieselPerHour, Double pumpingHoursSavedPerYear, Double structureCost) {
        this.annualHarvestedLitres = annualHarvestedLitres;
        this.tankerPricePer1000L = tankerPricePer1000L;
        this.electricityOrDieselPerHour = electricityOrDieselPerHour;
        this.pumpingHoursSavedPerYear = pumpingHoursSavedPerYear;
        this.structureCost = structureCost;
    }

    public Double getAnnualHarvestedLitres() {
        return this.annualHarvestedLitres;
    }

    public void setAnnualHarvestedLitres(Double annualHarvestedLitres) {
        this.annualHarvestedLitres = annualHarvestedLitres;
    }

    public Double getTankerPricePer1000L() {
        return this.tankerPricePer1000L;
    }

    public void setTankerPricePer1000L(Double tankerPricePer1000L) {
        this.tankerPricePer1000L = tankerPricePer1000L;
    }

    public Double getElectricityOrDieselPerHour() {
        return this.electricityOrDieselPerHour;
    }

    public void setElectricityOrDieselPerHour(Double electricityOrDieselPerHour) {
        this.electricityOrDieselPerHour = electricityOrDieselPerHour;
    }

    public Double getPumpingHoursSavedPerYear() {
        return this.pumpingHoursSavedPerYear;
    }

    public void setPumpingHoursSavedPerYear(Double pumpingHoursSavedPerYear) {
        this.pumpingHoursSavedPerYear = pumpingHoursSavedPerYear;
    }

    public Double getStructureCost() {
        return this.structureCost;
    }

    public void setStructureCost(Double structureCost) {
        this.structureCost = structureCost;
    }

}
