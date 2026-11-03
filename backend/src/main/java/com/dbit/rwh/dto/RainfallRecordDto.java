package com.dbit.rwh.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class RainfallRecordDto {
    private Long id;

    @NotNull(message = "Record date is required")
    private LocalDate recordDate;

    @NotNull(message = "Rainfall in mm is required")
    @PositiveOrZero(message = "Rainfall must be non-negative")
    private Double rainfallMm;

    private String district;
    private String taluka;
    private String note;

    public RainfallRecordDto() {}

    public RainfallRecordDto(Long id, LocalDate recordDate, Double rainfallMm, String district, String taluka, String note) {
        this.id = id;
        this.recordDate = recordDate;
        this.rainfallMm = rainfallMm;
        this.district = district;
        this.taluka = taluka;
        this.note = note;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRecordDate() {
        return this.recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public Double getRainfallMm() {
        return this.rainfallMm;
    }

    public void setRainfallMm(Double rainfallMm) {
        this.rainfallMm = rainfallMm;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTaluka() {
        return this.taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static RainfallRecordDtoBuilder builder() {
        return new RainfallRecordDtoBuilder();
    }

    public static class RainfallRecordDtoBuilder {
        private Long id;
        private LocalDate recordDate;
        private Double rainfallMm;
        private String district;
        private String taluka;
        private String note;

        public RainfallRecordDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RainfallRecordDtoBuilder recordDate(LocalDate recordDate) {
            this.recordDate = recordDate;
            return this;
        }

        public RainfallRecordDtoBuilder rainfallMm(Double rainfallMm) {
            this.rainfallMm = rainfallMm;
            return this;
        }

        public RainfallRecordDtoBuilder district(String district) {
            this.district = district;
            return this;
        }

        public RainfallRecordDtoBuilder taluka(String taluka) {
            this.taluka = taluka;
            return this;
        }

        public RainfallRecordDtoBuilder note(String note) {
            this.note = note;
            return this;
        }

        public RainfallRecordDto build() {
            RainfallRecordDto instance = new RainfallRecordDto();
            instance.setId(this.id);
            instance.setRecordDate(this.recordDate);
            instance.setRainfallMm(this.rainfallMm);
            instance.setDistrict(this.district);
            instance.setTaluka(this.taluka);
            instance.setNote(this.note);
            return instance;
        }
    }

}
