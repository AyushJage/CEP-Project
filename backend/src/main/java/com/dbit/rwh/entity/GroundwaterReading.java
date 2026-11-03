package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "groundwater_reading", indexes = {
    @Index(name = "idx_gw_district", columnList = "district"),
    @Index(name = "idx_gw_date", columnList = "reading_date")
})
public class GroundwaterReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String state; // Maharashtra

    @Column(nullable = false, length = 100)
    private String district;

    @Column(name = "well_id", length = 100)
    private String wellId;

    private Double latitude;
    private Double longitude;

    @Column(name = "well_depth_m")
    private Double wellDepthM;

    @Column(name = "aquifer_type", length = 100)
    private String aquiferType;

    @Column(name = "rainfall_mm")
    private Double rainfallMm;

    @Column(name = "groundwater_level_m", nullable = false)
    private Double groundwaterLevelM;

    @Column(name = "reading_date")
    private LocalDate readingDate;

    public GroundwaterReading() {}

    public GroundwaterReading(Long id, String state, String district, String wellId, Double latitude, Double longitude, Double wellDepthM, String aquiferType, Double rainfallMm, Double groundwaterLevelM, LocalDate readingDate) {
        this.id = id;
        this.state = state;
        this.district = district;
        this.wellId = wellId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.wellDepthM = wellDepthM;
        this.aquiferType = aquiferType;
        this.rainfallMm = rainfallMm;
        this.groundwaterLevelM = groundwaterLevelM;
        this.readingDate = readingDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWellId() {
        return this.wellId;
    }

    public void setWellId(String wellId) {
        this.wellId = wellId;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getWellDepthM() {
        return this.wellDepthM;
    }

    public void setWellDepthM(Double wellDepthM) {
        this.wellDepthM = wellDepthM;
    }

    public String getAquiferType() {
        return this.aquiferType;
    }

    public void setAquiferType(String aquiferType) {
        this.aquiferType = aquiferType;
    }

    public Double getRainfallMm() {
        return this.rainfallMm;
    }

    public void setRainfallMm(Double rainfallMm) {
        this.rainfallMm = rainfallMm;
    }

    public Double getGroundwaterLevelM() {
        return this.groundwaterLevelM;
    }

    public void setGroundwaterLevelM(Double groundwaterLevelM) {
        this.groundwaterLevelM = groundwaterLevelM;
    }

    public LocalDate getReadingDate() {
        return this.readingDate;
    }

    public void setReadingDate(LocalDate readingDate) {
        this.readingDate = readingDate;
    }

    public static GroundwaterReadingBuilder builder() {
        return new GroundwaterReadingBuilder();
    }

    public static class GroundwaterReadingBuilder {
        private Long id;
        private String state;
        private String district;
        private String wellId;
        private Double latitude;
        private Double longitude;
        private Double wellDepthM;
        private String aquiferType;
        private Double rainfallMm;
        private Double groundwaterLevelM;
        private LocalDate readingDate;

        public GroundwaterReadingBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public GroundwaterReadingBuilder state(String state) {
            this.state = state;
            return this;
        }

        public GroundwaterReadingBuilder district(String district) {
            this.district = district;
            return this;
        }

        public GroundwaterReadingBuilder wellId(String wellId) {
            this.wellId = wellId;
            return this;
        }

        public GroundwaterReadingBuilder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public GroundwaterReadingBuilder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public GroundwaterReadingBuilder wellDepthM(Double wellDepthM) {
            this.wellDepthM = wellDepthM;
            return this;
        }

        public GroundwaterReadingBuilder aquiferType(String aquiferType) {
            this.aquiferType = aquiferType;
            return this;
        }

        public GroundwaterReadingBuilder rainfallMm(Double rainfallMm) {
            this.rainfallMm = rainfallMm;
            return this;
        }

        public GroundwaterReadingBuilder groundwaterLevelM(Double groundwaterLevelM) {
            this.groundwaterLevelM = groundwaterLevelM;
            return this;
        }

        public GroundwaterReadingBuilder readingDate(LocalDate readingDate) {
            this.readingDate = readingDate;
            return this;
        }

        public GroundwaterReading build() {
            GroundwaterReading instance = new GroundwaterReading();
            instance.setId(this.id);
            instance.setState(this.state);
            instance.setDistrict(this.district);
            instance.setWellId(this.wellId);
            instance.setLatitude(this.latitude);
            instance.setLongitude(this.longitude);
            instance.setWellDepthM(this.wellDepthM);
            instance.setAquiferType(this.aquiferType);
            instance.setRainfallMm(this.rainfallMm);
            instance.setGroundwaterLevelM(this.groundwaterLevelM);
            instance.setReadingDate(this.readingDate);
            return instance;
        }
    }

}
