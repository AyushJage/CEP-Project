package com.dbit.rwh.dto;

import java.util.List;

public class CropSuggestionRequest {
    private Double nitrogen;
    private Double phosphorus;
    private Double potassium;
    private Double ph;
    private Double expectedRainfallMm;
    private Double temperatureC;
    private Double humidityPct;
    private String district;
    private String season; // KHARIF, RABI, ZAID

    public CropSuggestionRequest() {}

    public CropSuggestionRequest(Double nitrogen, Double phosphorus, Double potassium, Double ph, Double expectedRainfallMm, Double temperatureC, Double humidityPct, String district, String season) {
        this.nitrogen = nitrogen;
        this.phosphorus = phosphorus;
        this.potassium = potassium;
        this.ph = ph;
        this.expectedRainfallMm = expectedRainfallMm;
        this.temperatureC = temperatureC;
        this.humidityPct = humidityPct;
        this.district = district;
        this.season = season;
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

    public Double getPh() {
        return this.ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getExpectedRainfallMm() {
        return this.expectedRainfallMm;
    }

    public void setExpectedRainfallMm(Double expectedRainfallMm) {
        this.expectedRainfallMm = expectedRainfallMm;
    }

    public Double getTemperatureC() {
        return this.temperatureC;
    }

    public void setTemperatureC(Double temperatureC) {
        this.temperatureC = temperatureC;
    }

    public Double getHumidityPct() {
        return this.humidityPct;
    }

    public void setHumidityPct(Double humidityPct) {
        this.humidityPct = humidityPct;
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

}
