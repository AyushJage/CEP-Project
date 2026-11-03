package com.dbit.rwh.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "crop_profile")
public class CropProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crop_name_en", nullable = false)
    private String cropNameEn;

    @Column(name = "crop_name_mr", nullable = false)
    private String cropNameMr;

    @Column(nullable = false, length = 20)
    private String season; // KHARIF, RABI, ZAID

    @Column(name = "water_need", nullable = false, length = 15)
    private String waterNeed; // LOW, MEDIUM, HIGH

    @Column(name = "approx_water_mm")
    private Double approxWaterMm;

    @Column(name = "suited_regions")
    private String suitedRegions; // Konkan, Western Maharashtra, Marathwada, Vidarbha, Khandesh

    public CropProfile() {}

    public CropProfile(Long id, String cropNameEn, String cropNameMr, String season, String waterNeed, Double approxWaterMm, String suitedRegions) {
        this.id = id;
        this.cropNameEn = cropNameEn;
        this.cropNameMr = cropNameMr;
        this.season = season;
        this.waterNeed = waterNeed;
        this.approxWaterMm = approxWaterMm;
        this.suitedRegions = suitedRegions;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCropNameEn() {
        return this.cropNameEn;
    }

    public void setCropNameEn(String cropNameEn) {
        this.cropNameEn = cropNameEn;
    }

    public String getCropNameMr() {
        return this.cropNameMr;
    }

    public void setCropNameMr(String cropNameMr) {
        this.cropNameMr = cropNameMr;
    }

    public String getSeason() {
        return this.season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getWaterNeed() {
        return this.waterNeed;
    }

    public void setWaterNeed(String waterNeed) {
        this.waterNeed = waterNeed;
    }

    public Double getApproxWaterMm() {
        return this.approxWaterMm;
    }

    public void setApproxWaterMm(Double approxWaterMm) {
        this.approxWaterMm = approxWaterMm;
    }

    public String getSuitedRegions() {
        return this.suitedRegions;
    }

    public void setSuitedRegions(String suitedRegions) {
        this.suitedRegions = suitedRegions;
    }

    public static CropProfileBuilder builder() {
        return new CropProfileBuilder();
    }

    public static class CropProfileBuilder {
        private Long id;
        private String cropNameEn;
        private String cropNameMr;
        private String season;
        private String waterNeed;
        private Double approxWaterMm;
        private String suitedRegions;

        public CropProfileBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CropProfileBuilder cropNameEn(String cropNameEn) {
            this.cropNameEn = cropNameEn;
            return this;
        }

        public CropProfileBuilder cropNameMr(String cropNameMr) {
            this.cropNameMr = cropNameMr;
            return this;
        }

        public CropProfileBuilder season(String season) {
            this.season = season;
            return this;
        }

        public CropProfileBuilder waterNeed(String waterNeed) {
            this.waterNeed = waterNeed;
            return this;
        }

        public CropProfileBuilder approxWaterMm(Double approxWaterMm) {
            this.approxWaterMm = approxWaterMm;
            return this;
        }

        public CropProfileBuilder suitedRegions(String suitedRegions) {
            this.suitedRegions = suitedRegions;
            return this;
        }

        public CropProfile build() {
            CropProfile instance = new CropProfile();
            instance.setId(this.id);
            instance.setCropNameEn(this.cropNameEn);
            instance.setCropNameMr(this.cropNameMr);
            instance.setSeason(this.season);
            instance.setWaterNeed(this.waterNeed);
            instance.setApproxWaterMm(this.approxWaterMm);
            instance.setSuitedRegions(this.suitedRegions);
            return instance;
        }
    }

}
