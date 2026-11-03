package com.dbit.rwh.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FertilityReportDto {
    private Long id;
    private Double ph;
    private Double nitrogen;
    private Double phosphorus;
    private Double potassium;
    private Double organicCarbon;
    private Double ec;
    private Double score;
    private String band;
    private Map<String, String> parameterStatus;
    private List<String> tips;
    private LocalDateTime createdAt;

    public FertilityReportDto() {}

    public FertilityReportDto(Long id, Double ph, Double nitrogen, Double phosphorus, Double potassium, Double organicCarbon, Double ec, Double score, String band, Map<String, String> parameterStatus, List<String> tips, LocalDateTime createdAt) {
        this.id = id;
        this.ph = ph;
        this.nitrogen = nitrogen;
        this.phosphorus = phosphorus;
        this.potassium = potassium;
        this.organicCarbon = organicCarbon;
        this.ec = ec;
        this.score = score;
        this.band = band;
        this.parameterStatus = parameterStatus;
        this.tips = tips;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPh() {
        return this.ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
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

    public Double getOrganicCarbon() {
        return this.organicCarbon;
    }

    public void setOrganicCarbon(Double organicCarbon) {
        this.organicCarbon = organicCarbon;
    }

    public Double getEc() {
        return this.ec;
    }

    public void setEc(Double ec) {
        this.ec = ec;
    }

    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getBand() {
        return this.band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Map<String, String> getParameterStatus() {
        return this.parameterStatus;
    }

    public void setParameterStatus(Map<String, String> parameterStatus) {
        this.parameterStatus = parameterStatus;
    }

    public List<String> getTips() {
        return this.tips;
    }

    public void setTips(List<String> tips) {
        this.tips = tips;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static FertilityReportDtoBuilder builder() {
        return new FertilityReportDtoBuilder();
    }

    public static class FertilityReportDtoBuilder {
        private Long id;
        private Double ph;
        private Double nitrogen;
        private Double phosphorus;
        private Double potassium;
        private Double organicCarbon;
        private Double ec;
        private Double score;
        private String band;
        private Map<String, String> parameterStatus;
        private List<String> tips;
        private LocalDateTime createdAt;

        public FertilityReportDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public FertilityReportDtoBuilder ph(Double ph) {
            this.ph = ph;
            return this;
        }

        public FertilityReportDtoBuilder nitrogen(Double nitrogen) {
            this.nitrogen = nitrogen;
            return this;
        }

        public FertilityReportDtoBuilder phosphorus(Double phosphorus) {
            this.phosphorus = phosphorus;
            return this;
        }

        public FertilityReportDtoBuilder potassium(Double potassium) {
            this.potassium = potassium;
            return this;
        }

        public FertilityReportDtoBuilder organicCarbon(Double organicCarbon) {
            this.organicCarbon = organicCarbon;
            return this;
        }

        public FertilityReportDtoBuilder ec(Double ec) {
            this.ec = ec;
            return this;
        }

        public FertilityReportDtoBuilder score(Double score) {
            this.score = score;
            return this;
        }

        public FertilityReportDtoBuilder band(String band) {
            this.band = band;
            return this;
        }

        public FertilityReportDtoBuilder parameterStatus(Map<String, String> parameterStatus) {
            this.parameterStatus = parameterStatus;
            return this;
        }

        public FertilityReportDtoBuilder tips(List<String> tips) {
            this.tips = tips;
            return this;
        }

        public FertilityReportDtoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FertilityReportDto build() {
            FertilityReportDto instance = new FertilityReportDto();
            instance.setId(this.id);
            instance.setPh(this.ph);
            instance.setNitrogen(this.nitrogen);
            instance.setPhosphorus(this.phosphorus);
            instance.setPotassium(this.potassium);
            instance.setOrganicCarbon(this.organicCarbon);
            instance.setEc(this.ec);
            instance.setScore(this.score);
            instance.setBand(this.band);
            instance.setParameterStatus(this.parameterStatus);
            instance.setTips(this.tips);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
