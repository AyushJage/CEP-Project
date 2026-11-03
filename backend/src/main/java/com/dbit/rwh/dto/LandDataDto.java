package com.dbit.rwh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LandDataDto {
    private Long id;

    @NotBlank(message = "Plot name is required")
    private String plotName;

    @NotNull(message = "Area in square metres is required")
    @Positive(message = "Area must be positive")
    private Double areaSqm;

    @NotBlank(message = "Surface type is required")
    private String surfaceType;

    private String village;
    private String taluka;
    private String district;
    private String notes;

    public LandDataDto() {}

    public LandDataDto(Long id, String plotName, Double areaSqm, String surfaceType, String village, String taluka, String district, String notes) {
        this.id = id;
        this.plotName = plotName;
        this.areaSqm = areaSqm;
        this.surfaceType = surfaceType;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
        this.notes = notes;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static LandDataDtoBuilder builder() {
        return new LandDataDtoBuilder();
    }

    public static class LandDataDtoBuilder {
        private Long id;
        private String plotName;
        private Double areaSqm;
        private String surfaceType;
        private String village;
        private String taluka;
        private String district;
        private String notes;

        public LandDataDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LandDataDtoBuilder plotName(String plotName) {
            this.plotName = plotName;
            return this;
        }

        public LandDataDtoBuilder areaSqm(Double areaSqm) {
            this.areaSqm = areaSqm;
            return this;
        }

        public LandDataDtoBuilder surfaceType(String surfaceType) {
            this.surfaceType = surfaceType;
            return this;
        }

        public LandDataDtoBuilder village(String village) {
            this.village = village;
            return this;
        }

        public LandDataDtoBuilder taluka(String taluka) {
            this.taluka = taluka;
            return this;
        }

        public LandDataDtoBuilder district(String district) {
            this.district = district;
            return this;
        }

        public LandDataDtoBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public LandDataDto build() {
            LandDataDto instance = new LandDataDto();
            instance.setId(this.id);
            instance.setPlotName(this.plotName);
            instance.setAreaSqm(this.areaSqm);
            instance.setSurfaceType(this.surfaceType);
            instance.setVillage(this.village);
            instance.setTaluka(this.taluka);
            instance.setDistrict(this.district);
            instance.setNotes(this.notes);
            return instance;
        }
    }

}
