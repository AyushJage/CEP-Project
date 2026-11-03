package com.dbit.rwh.dto;

import jakarta.validation.constraints.NotBlank;

public class AiForecastRequest {
    @NotBlank(message = "District is required")
    private String district;

    private Integer month; // 1 - 12
    private String season; // KHARIF, RABI, ZAID

    public AiForecastRequest() {}

    public AiForecastRequest(String district, Integer month, String season) {
        this.district = district;
        this.month = month;
        this.season = season;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getMonth() {
        return this.month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getSeason() {
        return this.season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

}
